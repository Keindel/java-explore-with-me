package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.UserService;
import ru.practicum.explorewithme.util.ListModelMapper;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UsersApiController implements UsersApi {

    private final ModelMapper modelMapper;

    private final ListModelMapper listModelMapper;

    private final UserService userService;

    @GetMapping("/users/{userId}/events")
    public ResponseEntity<List<EventShortDto>> getEventsAddedByCurrentUser(@PathVariable("userId") Long userId,
                                                                           @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                                           @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(listModelMapper.mapList(userService
                        .getEventsAddedByCurrentUser(userId, from, size),
                EventShortDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}/events")
    public ResponseEntity<EventFullDto> updateEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                                 @Valid @RequestBody UpdateEventRequest updateEventRequest) {
        return new ResponseEntity<>(modelMapper.map(userService.updateEventOfCurrentUser(userId, updateEventRequest),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/events")
    public ResponseEntity<EventFullDto> addEvent(@PathVariable("userId") Long userId,
                                                 @Valid @RequestBody NewEventDto newEventDto) {
        return new ResponseEntity<>(modelMapper.map(userService.addEvent(userId, newEventDto),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> getEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                              @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(userService.getEventOfCurrentUser(userId, eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> cancelEventOfCurrentUser(@PathVariable("userId") Long userId,
                                                                 @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(userService.cancelEventOfCurrentUser(userId, eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/events/{eventId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getEventParticipants(@PathVariable("userId") Long userId,
                                                                              @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(listModelMapper.mapList(userService.getEventParticipants(userId, eventId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<ParticipationRequestDto> confirmParticipationRequest(@PathVariable("userId") Long userId,
                                                                               @PathVariable("eventId") Long eventId,
                                                                               @PathVariable("reqId") Long reqId) {
        return new ResponseEntity<>(modelMapper.map(userService.confirmParticipationRequest(userId, eventId, reqId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ResponseEntity<ParticipationRequestDto> rejectParticipationRequest(@PathVariable("userId") Long userId,
                                                                              @PathVariable("eventId") Long eventId,
                                                                              @PathVariable("reqId") Long reqId) {
        return new ResponseEntity<>(modelMapper.map(userService.rejectParticipationRequest(userId, eventId, reqId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getUserRequests(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(listModelMapper.mapList(userService.getUserRequests(userId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/requests")
    public ResponseEntity<ParticipationRequestDto> addParticipationRequest(@PathVariable("userId") Long userId,
                                                                           @NotNull @Valid @RequestParam(value = "eventId", required = true) Long eventId) {
        return new ResponseEntity<>(modelMapper.map(userService.addParticipationRequest(userId, eventId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}/requests/{requestId}/cancel")
    public ResponseEntity<ParticipationRequestDto> cancelRequest(@PathVariable("userId") Long userId,
                                                                 @PathVariable("requestId") Long requestId) {
        return new ResponseEntity<>(modelMapper.map(userService.cancelRequest(userId, requestId),
                ParticipationRequestDto.class),
                HttpStatus.OK);
    }
}
