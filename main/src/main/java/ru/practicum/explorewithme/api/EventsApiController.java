package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.service.EventService;
import ru.practicum.explorewithme.util.ListModelMapper;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventsApiController implements EventsApi {

    private final ModelMapper modelMapper;

    private final ListModelMapper listModelMapper;

    private final EventService eventService;

    //TODO
    private final WebClient webClient = WebClient.create("${statistics-server.url}");

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable("id") Long id) {
        Event event = eventService.getEventById(id);

        //TODO
        Long views = webClient.get()
                .uri("/stats?start={start}&end={end}&uris={uris}&unique={unique}")
                .retrieve()
                .bodyToMono(ViewStats.class)
                .getHits();

        EventFullDto eventFullDto = modelMapper.map(eventService.getEventById(id), EventFullDto.class);
        eventFullDto.setViews(views);

        return new ResponseEntity<>(eventFullDto,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getEventsShort(@Valid @RequestParam(value = "text", required = false) String text,
                                                              @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
                                                              @Valid @RequestParam(value = "paid", required = false) Boolean paid,
                                                              @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                                              @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                                              @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
                                                              @Valid @RequestParam(value = "sort", required = false) String sort,
                                                              @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                              @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        //TODO STATS - HOW ???
        return new ResponseEntity<>(listModelMapper.mapList(eventService
                        .getEventsShort(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size),
                EventShortDto.class),
                HttpStatus.OK);
    }
}