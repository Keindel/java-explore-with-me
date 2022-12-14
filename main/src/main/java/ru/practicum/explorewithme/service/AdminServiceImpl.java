package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.LocationAreaNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.location.LocationArea;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.*;
import ru.practicum.explorewithme.util.CustomPageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final CompilationRepository compilationRepository;

    private final EventRepository eventRepository;

    private final ParticipationRequestRepository participationRequestRepository;

    private final LocationAreaRepository locationAreaRepository;

    @Override
    @Transactional
    public Category addCategory(NewCategoryDto newCategoryDto) {
        return categoryRepository.save(modelMapper.map(newCategoryDto, Category.class));
    }

    @Override
    @Transactional
    public void deleteCategory(Long catId) {
        categoryRepository.deleteById(catId);
    }

    @Override
    @Transactional
    public Category updateCategory(CategoryDto categoryDto) {
        return categoryRepository.save(modelMapper.map(categoryDto, Category.class));
    }

    @Override
    @Transactional
    public User registerUser(NewUserRequest newUserRequest) {
        return userRepository.save(modelMapper.map(newUserRequest, User.class));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        Pageable page = CustomPageable.of(from, size);
        if (ids != null) {
            return userRepository.findAllByIdList(ids, page).getContent();
        }
        return userRepository.findAll(page).getContent();
    }

    @Override
    public List<Event> getEventsDetailed(List<Long> users,
                                         List<State> states,
                                         List<Long> categories,
                                         LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                         Integer from, Integer size) throws EventTimeException {
        Pageable page = CustomPageable.of(from, size);
        rangeStart = (rangeStart == null) ? LocalDateTime.MIN : rangeStart;
        rangeEnd = (rangeEnd == null) ? LocalDateTime.MAX : rangeEnd;
        if (rangeStart.isAfter(rangeEnd)) {
            throw new EventTimeException("start must be before end");
        }

        return eventRepository.findAllByParamsCustom(users, states, categories, rangeStart, rangeEnd, page).getContent();
    }

    @Override
    @Transactional
    public Event updateEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest)
            throws EventNotFoundException, RequestLogicException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Integer newParticipantLimit = adminUpdateEventRequest.getParticipantLimit();
        Long checkNumber = participationRequestRepository.countByStatusAndEvent(Status.CONFIRMED, event);
        if (newParticipantLimit != null
                && newParticipantLimit != 0
                && newParticipantLimit < checkNumber) {
            throw new RequestLogicException("new request limit can't be less than current number of confirmed requests");
        }
        Event eventUpdate = eventMapper.mapFromUpdateToEvent(adminUpdateEventRequest, event);
        return eventRepository.save(eventUpdate);
    }

    @Override
    @Transactional
    public Event publishEvent(Long eventId) throws EventNotFoundException, ForbiddenException, EventTimeException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        if (event.getState() != State.PENDING) {
            throw new ForbiddenException("can publish only pending-state events");
        }
        // ???????? ???????????? ?????????????? ???????????? ???????? ???? ?????????? ?????? ???? ?????? ???? ???????? ????????????????????.
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new EventTimeException("cant publish event with start within an hour");
        }
        event.setState(State.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event rejectEvent(Long eventId) throws ForbiddenException, EventNotFoundException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        if (event.getState() == State.PUBLISHED) {
            throw new ForbiddenException("can not reject pubslihed event");
        }
        event.setState(State.CANCELED);
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Compilation saveCompilation(NewCompilationDto newCompilationDto) throws EventNotFoundException {
        List<Long> eventIds = newCompilationDto.getEvents();
        List<Event> events = new ArrayList<>();
        for (Long eventId : eventIds) {
            events.add(eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId)));
        }
        Compilation compilation = modelMapper.map(newCompilationDto, Compilation.class);
        compilation.setEvents(events);
        return compilationRepository.save(compilation);
    }

    @Override
    @Transactional
    public void deleteCompilation(Long compId) {
        compilationRepository.deleteById(compId);
    }

    @Override
    @Transactional
    public void removeEventFromCompilation(Long compId, Long eventId)
            throws EventNotFoundException, CompilationNotFoundException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new CompilationNotFoundException(compId));
        compilation.getEvents().remove(event);
        compilationRepository.save(compilation);
    }

    @Override
    @Transactional
    public void addEventToCompilation(Long compId, Long eventId)
            throws EventNotFoundException, CompilationNotFoundException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new CompilationNotFoundException(compId));
        compilation.getEvents().add(event);
        compilationRepository.save(compilation);
    }

    @Override
    @Transactional
    public void unpinCompilation(Long compId) throws CompilationNotFoundException {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new CompilationNotFoundException(compId));
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    @Transactional
    public void pinCompilation(Long compId) throws CompilationNotFoundException {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new CompilationNotFoundException(compId));
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }

    @Override
    public LocationArea addArea(LocationArea locationArea) {
        return locationAreaRepository.save(locationArea);
    }

    @Override
    public void deleteArea(Long areaId) {
        locationAreaRepository.deleteById(areaId);
    }

    @Override
    public List<LocationArea> getAreas() {
        return locationAreaRepository.findAll();
    }

    @Override
    public List<Event> getEventsInArea(Long areaId, Integer from, Integer size) throws LocationAreaNotFoundException {
        LocationArea locationArea = locationAreaRepository.findById(areaId).orElseThrow(()
                -> new LocationAreaNotFoundException(areaId));
        BigDecimal lat = locationArea.getLat();
        BigDecimal lon = locationArea.getLon();
        Integer radiusInKm = locationArea.getRadius();
        Pageable page = CustomPageable.of(from, size);
        return eventRepository.findAllByAreaParams(lat, lon, radiusInKm, page).getContent();
    }
}
