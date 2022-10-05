package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.ParticipationRequestNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.UserNotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;

import java.util.List;

public interface UserService {

    List<Event> getEventsAddedByCurrentUser(Long userId, Integer from, Integer size);

    Event updateEventOfCurrentUser(Long userId, UpdateEventRequest updateEventRequest) throws EventNotFoundException, ForbiddenException, RequestLogicException;

    Event addEvent(Long userId, NewEventDto newEventDto) throws EventTimeException, UserNotFoundException, CategoryNotFoundException;

    Event getEventOfCurrentUser(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, ForbiddenException;

    Event cancelEventOfCurrentUser(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, ForbiddenException;

    List<ParticipationRequest> getEventParticipants(Long userId, Long eventId) throws UserNotFoundException, ForbiddenException, EventNotFoundException;

    ParticipationRequest confirmParticipationRequest(Long userId, Long eventId, Long reqId) throws UserNotFoundException, EventNotFoundException, ParticipationRequestNotFoundException, ForbiddenException, RequestLogicException;

    ParticipationRequest rejectParticipationRequest(Long userId, Long eventId, Long reqId) throws UserNotFoundException, ForbiddenException, EventNotFoundException, ParticipationRequestNotFoundException;

    List<ParticipationRequest> getUserRequests(Long userId) throws UserNotFoundException;

    ParticipationRequest addParticipationRequest(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, RequestLogicException;

    ParticipationRequest cancelRequest(Long userId, Long requestId) throws UserNotFoundException, ParticipationRequestNotFoundException, ForbiddenException;
}
