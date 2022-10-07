package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.service.EventService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventsApiController implements EventsApi {

    private final EventMapper eventMapper;

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getEventsShort(@Valid @RequestParam(value = "text", required = false) String text,
                                                              @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
                                                              @Valid @RequestParam(value = "paid", required = false) Boolean paid,
                                                              @Valid @RequestParam(value = "rangeStart", required = false)
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                              @Valid @RequestParam(value = "rangeEnd", required = false)
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                              @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
                                                              @Valid @RequestParam(value = "sort", required = false) String stringSort,
                                                              @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                              @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size)
            throws EventTimeException {
        return new ResponseEntity<>(eventService
                .getEventsShort(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, stringSort, from, size)
                .stream().map(eventMapper::mapToShortDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable("id") Long id)
            throws EventNotFoundException, EventTimeException {
        return new ResponseEntity<>(eventService.getEventById(id),
                HttpStatus.OK);
    }
}