package ru.practicum.explorewithme.api.forprivate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.ParticipationRequestNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.UserNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.mapper.ParticipationMapper;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.UserService;
import ru.practicum.explorewithme.mapper.ListModelMapper;
import ru.practicum.explorewithme.statsclient.UriListMaker;
import ru.practicum.explorewithme.statsclient.ViewsStatsRetriever;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UsersApiController implements UsersApi {

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    private final ParticipationMapper participationMapper;

    private final ListModelMapper listModelMapper;

    private final UserService userService;

    private final UriListMaker uriListMaker;

    private final ViewsStatsRetriever viewsStatsRetriever;

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<EventShortDto>> getEventsAddedByCurrentUser(@PathVariable("userId") Long userId,
                                                                           @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                                           @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        List<Event> eventList = userService.getEventsAddedByCurrentUser(userId, from, size);
        return new ResponseEntity<>(eventMapper.mapToEventShortDtoList(eventList,
                viewsStatsRetriever.retrieveViewsList(uriListMaker.make(eventList))),
                HttpStatus.OK);
    }

    @PatchMapping("/{userId}/events")
    public ResponseEntity<EventFullDto> updateEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                                 @Valid @RequestBody UpdateEventRequest updateEventRequest)
            throws ForbiddenException, RequestLogicException, EventNotFoundException {

        Event event = userService.updateEventOfCurrentUser(userId, updateEventRequest);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    @PostMapping("/{userId}/events")
    public ResponseEntity<EventFullDto> addEvent(@PathVariable("userId") Long userId,
                                                 @Valid @RequestBody NewEventDto newEventDto)
            throws EventTimeException, UserNotFoundException, CategoryNotFoundException {

        Event event = userService.addEvent(userId, newEventDto);
        // при создании число просмотров 0
        return new ResponseEntity<>(eventMapper.mapToFullDto(event, 0L),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> getEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                              @PathVariable("eventId") Long eventId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException {

        Event event = userService.getEventOfCurrentUser(userId, eventId);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    private EventFullDto composeEventFullDto(Event event) {
        return eventMapper.mapToFullDto(event,
                viewsStatsRetriever.retrieveHitsForEvent(uriListMaker.make(List.of(event)), event));
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> cancelEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                                 @PathVariable("eventId") Long eventId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException {

        Event event = userService.cancelEventOfCurrentUser(userId, eventId);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getEventParticipants(@PathVariable("userId") Long userId,
                                                                              @PathVariable("eventId") Long eventId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException {
        return new ResponseEntity<>(listModelMapper.mapList(userService.getEventParticipants(userId, eventId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<ParticipationRequestDto> confirmParticipationRequest(@PathVariable("userId") Long userId,
                                                                               @PathVariable("eventId") Long eventId,
                                                                               @PathVariable("reqId") Long reqId)
            throws UserNotFoundException, ForbiddenException, RequestLogicException, EventNotFoundException, ParticipationRequestNotFoundException {
        return new ResponseEntity<>(modelMapper.map(userService.confirmParticipationRequest(userId, eventId, reqId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ResponseEntity<ParticipationRequestDto> rejectParticipationRequest(@PathVariable("userId") Long userId,
                                                                              @PathVariable("eventId") Long eventId,
                                                                              @PathVariable("reqId") Long reqId)
            throws UserNotFoundException, ForbiddenException, EventNotFoundException, ParticipationRequestNotFoundException {
        return new ResponseEntity<>(modelMapper.map(userService.rejectParticipationRequest(userId, eventId, reqId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getUserRequests(@PathVariable("userId") Long userId)
            throws UserNotFoundException {
        return new ResponseEntity<>(listModelMapper.mapList(userService.getUserRequests(userId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PostMapping("/{userId}/requests")
    public ResponseEntity<ParticipationRequestDto> addParticipationRequest(
            @PathVariable("userId") Long userId,
            @NotNull @Valid @RequestParam(value = "eventId") Long eventId)
            throws UserNotFoundException, RequestLogicException, EventNotFoundException {
        return new ResponseEntity<>(participationMapper
                .mapToParticipationRequestDto(userService.addParticipationRequest(userId, eventId)),
                HttpStatus.OK);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ResponseEntity<ParticipationRequestDto> cancelRequest(@PathVariable("userId") Long userId,
                                                                 @PathVariable("requestId") Long requestId)
            throws UserNotFoundException, ForbiddenException, ParticipationRequestNotFoundException {
        return new ResponseEntity<>(modelMapper.map(userService.cancelRequest(userId, requestId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }
}
