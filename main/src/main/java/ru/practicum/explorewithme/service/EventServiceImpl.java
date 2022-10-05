package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final CategoryRepository categoryRepository;

    private final WebClient statsClient = WebClient.create("${statistics-server.url}");

    @Override
    public List<Event> getEventsShort(String text,
                                      List<Long> categories,
                                      Boolean paid,
                                      LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                      Boolean onlyAvailable,
                                      String stringSort,
                                      Integer from, Integer size) throws EventTimeException {
        /*
        * это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
        * текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
        * */

        //TODO
        postEventsHitToStats();

        rangeStart = (rangeStart == null ? LocalDateTime.now() : rangeStart);
        rangeEnd = (rangeEnd == null ? LocalDateTime.MAX :rangeEnd);
        if (rangeStart.isAfter(rangeEnd)) {
            throw new EventTimeException("start must be before end");
        }
        Sort sort = Sort.sort(Event.class).by(Event::getEventDate).descending();
        Pageable page = CustomPageable.of(from, size, sort);

        //TODO
        Long views = statsClient.get()
                .uri("/stats?start={start}&end={end}&uris={uris}&unique={unique}")
                .retrieve()
                .bodyToMono(ViewStats.class)
                .block()
                .getHits();

        switch (stringSort) {
            case "EVENT_DATE":
                break;
            case "VIEWS":
                break;
            default:
                throw new IllegalArgumentException(stringSort + " - not supported");
        }
        return null;//eventRepository.findAllBy;
    }

    private void postEventsHitToStats() {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHit(0L, "main-server", "/events", "IP", LocalDateTime.now()))
                .retrieve();
    }

    @Override
    public Event getEventById(Long id) throws EventNotFoundException {
        return eventRepository.findByIdAndPublishedOnIsNotNull(id).orElseThrow(()
                -> new EventNotFoundException(id));
    }
}
