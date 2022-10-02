package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.UserNotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.LocationRepository;
import ru.practicum.explorewithme.repository.ParticipationRequestRepository;
import ru.practicum.explorewithme.repository.UserRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

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
    public Event updateEventOfCurrentUser(Long userId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public Event addEvent(Long userId, NewEventDto newEventDto) throws EventTimeException, UserNotFoundException {
        if (newEventDto.getEventDate().minusHours(2).isBefore(LocalDateTime.now())) {
            throw new EventTimeException("Only pending or canceled events can be changed");
        }
        Event event = modelMapper.map(newEventDto, Event.class);
        Location location = locationRepository.save(newEventDto.getLocation());

        event.setLocation(location);
        event.setCreatedOn(LocalDateTime.now());
        event.setInitiator(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id=%d was not found.", userId))));
        return eventRepository.save(event);
    }

    @Override
    public Event getEventOfCurrentUser(Long userId, Long eventId) {
        return null;
    }

    @Override
    public Event cancelEventOfCurrentUser(Long userId, Long eventId) {
        return null;
    }

    @Override
    public List<ParticipationRequest> getEventParticipants(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequest confirmParticipationRequest(Long userId, Long eventId, Long reqId) {
        return null;
    }

    @Override
    public ParticipationRequest rejectParticipationRequest(Long userId, Long eventId, Long reqId) {
        return null;
    }

    @Override
    public List<ParticipationRequest> getUserRequests(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequest addParticipationRequest(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, RequestLogicException {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        if (event.getInitiator().equals(currentUser)) {
            throw new RequestLogicException("Initiator cant request");
        }
        if (event.getPublishedOn() == null) {
            throw new RequestLogicException("Cant request not published event");
        }
        if (event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new RequestLogicException("Requests limit is reached");
        }
        Status status = Status.PENDING;
        if (Boolean.FALSE.equals(event.getRequestModeration())) {
            status = Status.CONFIRMED;
        }
        ParticipationRequest participationRequest = new ParticipationRequest(LocalDateTime.now(),
                event, null, currentUser, status);

        return participationRequestRepository.save(participationRequest);
    }

    @Override
    public ParticipationRequest cancelRequest(Long userId, Long requestId) {
        return null;
    }

    private void validateUserPresence (Long userId) throws UserNotFoundException {
        if (! userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    private void validateEventPresence (Long eventId) throws EventNotFoundException {
        if (! eventRepository.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
    }
}
