package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.service.EventService;
import ru.practicum.explorewithme.mapper.ListModelMapper;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    private final WebClient statsClient = WebClient.create("${statistics-server.url}");

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable("id") Long id) throws EventNotFoundException {
        Event event = eventService.getEventById(id);

        //информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов

        //TODO
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHit(0L, "main-server", "/events/{id}", "IP", LocalDateTime.now()))
                .retrieve();

        //TODO
        Long views = statsClient.get()
                .uri("/stats?start={start}&end={end}&uris={uris}&unique={unique}")
                .retrieve()
                .bodyToMono(ViewStats.class)
                .block()
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
                                                              @Valid @RequestParam(value = "rangeStart", required = false)
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                              @Valid @RequestParam(value = "rangeEnd", required = false)
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                              @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
                                                              @Valid @RequestParam(value = "sort", required = false) String stringSort,
                                                              @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                              @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws EventTimeException {
        //TODO STATS - HOW ???
        /*
         * информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
         * информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
         * */




        return new ResponseEntity<>(listModelMapper.mapList(eventService
                        .getEventsShort(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, stringSort, from, size),
                EventShortDto.class),
                HttpStatus.OK);
    }
}