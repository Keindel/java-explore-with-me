package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.UserNotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;

import java.util.List;

public interface UserService {

    List<Event> getEventsAddedByCurrentUser(Long userId, Integer from, Integer size);

    Event updateEventOfCurrentUser(Long userId, UpdateEventRequest updateEventRequest);

    Event addEvent(Long userId, NewEventDto newEventDto) throws EventTimeException, UserNotFoundException;

    Event getEventOfCurrentUser(Long userId, Long eventId);

    Event cancelEventOfCurrentUser(Long userId, Long eventId);

    List<ParticipationRequest> getEventParticipants(Long userId, Long eventId);

    ParticipationRequest confirmParticipationRequest(Long userId, Long eventId, Long reqId);

    ParticipationRequest rejectParticipationRequest(Long userId, Long eventId, Long reqId);

    List<ParticipationRequest> getUserRequests(Long userId);

    ParticipationRequest addParticipationRequest(Long userId, Long eventId) throws UserNotFoundException, EventNotFoundException, RequestLogicException;

    ParticipationRequest cancelRequest(Long userId, Long requestId);
}
