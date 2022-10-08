package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
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

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    @Override
    public List<Event> getEventsShort(String text,
                                      List<Long> categories,
                                      Boolean paid,
                                      LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                      Boolean onlyAvailable,
                                      String stringSort,
                                      Integer from, Integer size) throws EventTimeException {

        //TODO STATS - HOW ???
        /*
         * информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
         * информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
         * */
        /*
        * это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
        * текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
        * */
        rangeStart = (rangeStart == null ? LocalDateTime.now() : rangeStart);
        rangeEnd = (rangeEnd == null ? LocalDateTime.MAX :rangeEnd);
        if (rangeStart.isAfter(rangeEnd)) {
            throw new EventTimeException("start must be before end");
        }
        Sort sort = Sort.sort(Event.class).by(Event::getEventDate).descending();
        Pageable page = CustomPageable.of(from, size, sort);

        //TODO
//        postEventsHitToStats();

        switch (stringSort) {
            case "EVENT_DATE":
                break;
            case "VIEWS":
                break;
            default:
                throw new IllegalArgumentException(stringSort + " - not supported");
        }
        return eventRepository.findAllByPublicParams(text,
                categories,
                paid,
                rangeStart, rangeEnd,
                onlyAvailable, page).getContent();
    }

    //TODO перенести POST Hit в контроллер?
    private void postEventsHitToStats() {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHit(0L, "main-server", "/events", "IP", LocalDateTime.now()))
                .retrieve();
    }

    //TODO перенести POST Hit в контроллер?
    private void postEventIdHitToStats(Long id) {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHit(0L, "main-server", "/event/" + id, "IP", LocalDateTime.now()))
                .retrieve();
    }

    @Override
    public EventFullDto getEventById(Long id) throws EventNotFoundException, EventTimeException {
        Event event = eventRepository.findByIdAndPublishedOnIsNotNull(id).orElseThrow(()
                -> new EventNotFoundException(id));

        //информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов

        /*postEventIdHitToStats(id);
        //TODO
        Long views = statsClient.get()
                .uri("/stats?start={start}&end={end}&uris={uris}&unique={unique}")
                .retrieve()
                .bodyToMono(ViewStats.class)
                .block()
                .getHits();
*/
        EventFullDto eventFullDto = eventMapper.mapToFullDto(event);
//        eventFullDto.setViews(views);
        return eventFullDto;
    }
}
