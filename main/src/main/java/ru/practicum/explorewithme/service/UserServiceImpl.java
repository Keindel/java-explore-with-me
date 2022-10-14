package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.ParticipationRequestNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.UserNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.*;
import ru.practicum.explorewithme.util.CustomPageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EventMapper eventMapper;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final LocationRepository locationRepository;

    private final ParticipationRequestRepository participationRequestRepository;

    @Override
    public List<Event> getEventsAddedByCurrentUser(Long userId, Integer from, Integer size) {
        Pageable page = CustomPageable.of(from, size, Sort.sort(Event.class).by(Event::getCreatedOn).descending());
        return eventRepository.findAllByInitiatorId(userId, page).getContent();
    }

    @Override
    @Transactional
    public Event updateEventOfCurrentUser(Long userId, UpdateEventRequest updateEventRequest)
            throws EventNotFoundException, ForbiddenException, RequestLogicException {
        Long eventId = updateEventRequest.getEventId();
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new EventNotFoundException(eventId));
        validateUpdateEvent(userId, event);
        Integer newParticipantLimit = updateEventRequest.getParticipantLimit();
        if (newParticipantLimit != null
                && newParticipantLimit < participationRequestRepository.findAllByStatusAndEvent(Status.CONFIRMED, event).size()) {
            throw new RequestLogicException("new request limit can't be less than current number of confirmed requests");
        }
        Event eventUpdate = eventMapper.mapFromUpdateToEvent(updateEventRequest, event);
        eventUpdate.setState(State.PENDING);
        return eventRepository.save(eventUpdate);
    }

    private static void validateUpdateEvent(Long userId, Event event) throws ForbiddenException {
        if (!Objects.equals(event.getInitiator().getId(), userId)) {
            throw new ForbiddenException("no access rights for current user");
        }
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ForbiddenException("event starts too soon for changes");
        }
        State eventState = event.getState();
        // изменить можно только отмененные события или события в состоянии ожидания модерации
        if (eventState == State.PUBLISHED) {
            throw new ForbiddenException("event state forbids further changes");
        }
    }

    @Override
    @Transactional
    public Event addEvent(Long userId, NewEventDto newEventDto) throws EventTimeException, UserNotFoundException {
        if (newEventDto.getEventDate().minusHours(2).isBefore(LocalDateTime.now())) {
            throw new EventTimeException("Event date shall be at least 2 hours later than current time");
        }
        Event event = eventMapper.mapToEvent(newEventDto, null);
        Location location = locationRepository.save(newEventDto.getLocation());

        event.setLocation(location);
        event.setState(State.PENDING);
        event.setCreatedOn(LocalDateTime.now());
        event.setInitiator(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)));
        return eventRepository.save(event);
    }

    @Override
    public Event getEventOfCurrentUser(Long userId, Long eventId)
            throws UserNotFoundException, EventNotFoundException, ForbiddenException {
        return getEventCheckedByOwner(userId, eventId);
    }

    private Event getEventCheckedByOwner(Long userId, Long eventId)
            throws UserNotFoundException, EventNotFoundException, ForbiddenException {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(userId));
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new EventNotFoundException(eventId));
        if (event.getInitiator() != user) {
            throw new ForbiddenException("event does not belong to current user");
        }
        return event;
    }

    @Override
    @Transactional
    public Event cancelEventOfCurrentUser(Long userId, Long eventId)
            throws UserNotFoundException, EventNotFoundException, ForbiddenException {
        Event event = getEventCheckedByOwner(userId, eventId);
        if (event.getState() != State.PENDING) {
            throw new ForbiddenException("event state forbids cancellation");
        }
        event.setState(State.CANCELED);
        return eventRepository.save(event);
    }

    @Override
    public List<ParticipationRequest> getEventParticipants(Long userId, Long eventId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException {
        Event event = getEventCheckedByOwner(userId, eventId);
        return participationRequestRepository.findAllByEvent(event);
    }

    @Override
    @Transactional
    public ParticipationRequest confirmParticipationRequest(Long userId, Long eventId, Long reqId)
            throws UserNotFoundException, EventNotFoundException, ParticipationRequestNotFoundException, ForbiddenException, RequestLogicException {
        Event event = getEventCheckedByOwner(userId, eventId);
        ParticipationRequest req = participationRequestRepository.findById(reqId).orElseThrow(()
                -> new ParticipationRequestNotFoundException(reqId));
        // если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется
        if (Boolean.FALSE.equals(event.getRequestModeration())
                || event.getParticipantLimit() == 0) {
            return req;
        }
        int confirmedRequestsNumber = participationRequestRepository.findAllByStatusAndEvent(Status.CONFIRMED, event).size();
        // нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
        if (confirmedRequestsNumber >= event.getParticipantLimit()
                && event.getParticipantLimit() != 0) {
            throw new RequestLogicException("Requests limit is reached");
        }
        req.setStatus(Status.CONFIRMED);
        req = participationRequestRepository.save(req);
        confirmedRequestsNumber++;
        // если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить
        if (confirmedRequestsNumber >= event.getParticipantLimit()) {
            participationRequestRepository.findAllByStatusAndEvent(Status.PENDING, event)
                    .stream().peek(r -> r.setStatus(Status.CANCELED)).forEach(participationRequestRepository::save);
        }
        return req;
    }

    @Override
    @Transactional
    public ParticipationRequest rejectParticipationRequest(Long userId, Long eventId, Long reqId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException, ParticipationRequestNotFoundException {
        Event event = getEventCheckedByOwner(userId, eventId);
        ParticipationRequest req = participationRequestRepository.findById(reqId).orElseThrow(()
                -> new ParticipationRequestNotFoundException(reqId));
        if (req.getEvent() != event) {
            throw new ForbiddenException("request does not belong to the event");
        }
        req.setStatus(Status.REJECTED);
        return participationRequestRepository.save(req);
    }

    @Override
    public List<ParticipationRequest> getUserRequests(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return participationRequestRepository.findAllByRequester(user);
    }

    @Override
    @Transactional
    public ParticipationRequest addParticipationRequest(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, RequestLogicException {
        // нельзя добавить повторный запрос - реализовано на уровне БД как UNIQUE(user_id, event_id)
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        validateParticipationRequest(currentUser, event);
        Status status = Status.PENDING;
        // если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного
        if (Boolean.FALSE.equals(event.getRequestModeration())) {
            status = Status.CONFIRMED;
        }
        ParticipationRequest participationRequest = new ParticipationRequest(LocalDateTime.now(),
                event, null, currentUser, status);

        return participationRequestRepository.save(participationRequest);
    }

    private void validateParticipationRequest(User currentUser, Event event) throws RequestLogicException {
        // инициатор события не может добавить запрос на участие в своём событии
        if (event.getInitiator().equals(currentUser)) {
            throw new RequestLogicException("Initiator cant request");
        }
        // нельзя участвовать в неопубликованном событии
        if (event.getPublishedOn() == null) {
            throw new RequestLogicException("Cant request not published event");
        }
        // если у события достигнут лимит запросов на участие - необходимо вернуть ошибку
//        if (participationRequestRepository.findAllByStatusAndEvent(Status.CONFIRMED, event).size()
        if (participationRequestRepository.countByStatusAndEvent(Status.CONFIRMED, event)
                >= event.getParticipantLimit()
                && event.getParticipantLimit() != 0) {
            throw new RequestLogicException("Requests limit is reached");
        }
    }

    @Override
    @Transactional
    public ParticipationRequest cancelRequest(Long userId, Long requestId)
            throws UserNotFoundException, ParticipationRequestNotFoundException, ForbiddenException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        ParticipationRequest req = participationRequestRepository.findById(requestId).orElseThrow(()
        -> new ParticipationRequestNotFoundException(requestId));
        if (req.getRequester() != user) {
            throw new ForbiddenException("request does not belong to the user");
        }
        req.setStatus(Status.CANCELED);
        return req;
    }
}
