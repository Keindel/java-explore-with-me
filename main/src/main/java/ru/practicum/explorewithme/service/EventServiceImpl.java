package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
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
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

//    private final WebClient statsClient = WebClient.create("${statistics-server.url}");
    private final WebClient statsClient = WebClient.create("http://localhost:5432/exploredb");

    private final EventMapper eventMapper;

    @Override
    public List<EventShortDto> getEventsShort(String text,
                                              List<Long> categories,
                                              Boolean paid,
                                              LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                              Boolean onlyAvailable,
                                              String stringSort,
                                              Integer from, Integer size,
                                              HttpServletRequest httpServletRequest) throws EventTimeException {
        //TODO check
        postHitToStats(httpServletRequest);

        rangeStart = (rangeStart == null ? LocalDateTime.now() : rangeStart);
        rangeEnd = (rangeEnd == null ? LocalDateTime.MAX : rangeEnd);
        if (rangeStart.isAfter(rangeEnd)) {
            throw new EventTimeException("start must be before end");
        }

        List<Event> eventList;
        Sort sort;
        Pageable page;
        switch (stringSort) {
            case "EVENT_DATE":
                sort = Sort.sort(Event.class).by(Event::getEventDate).descending();
                page = CustomPageable.of(from, size, sort);
                eventList = eventRepository.findAllByPublicParamsCustom(text,
                        categories,
                        paid,
                        rangeStart, rangeEnd,
                        onlyAvailable, page).getContent();
                break;
            case "VIEWS":
                page = CustomPageable.of(from, size);
                eventList = eventRepository.findAllByPublicParamsCustom(text,
                        categories,
                        paid,
                        rangeStart, rangeEnd,
                        onlyAvailable, page).getContent();
                break;
            default:
                throw new IllegalArgumentException(stringSort + " - not supported");
        }
        List<EventShortDto> eventShortDtoList = eventList.stream()
                .map(eventMapper::mapToShortDto).collect(Collectors.toList());

        List<String> uriList = new ArrayList<>();
        eventShortDtoList.forEach(eventShortDto -> uriList.add("/events/" + eventShortDto.getId()));

        //TODO check
        List<ViewStats> viewStatsList = statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("uris", uriList)
                        .build())
                .retrieve()
                .bodyToFlux(ViewStats.class)
                .collect(Collectors.toList())
                .block();

        // TODO getEventViewsById(Long id)
//        eventShortDtoList.forEach(eventShortDto -> eventShortDto.setViews(getEventViewsById(eventShortDto.getId())));

        //TODO STATS - get
        //   "/stats?start={start}&end={end}&uris={uris}&unique={unique}")

        if (page.getSort().isUnsorted()) {
            eventShortDtoList = eventShortDtoList.stream()
                    .sorted(Comparator.comparingLong(EventShortDto::getViews).reversed())
                    .skip(from)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        return eventShortDtoList;
    }

    private void postHitToStats(HttpServletRequest request) {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHit(0L,
                        "main-server",
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        LocalDateTime.now()))
                .retrieve();
    }

    @Override
    public EventFullDto getEventById(Long id, HttpServletRequest httpServletRequest)
            throws EventNotFoundException {
        Event event = eventRepository.findByIdAndPublishedOnIsNotNull(id).orElseThrow(()
                -> new EventNotFoundException(id));

        postHitToStats(httpServletRequest);
        //TODO check
        Long views = statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("uris", List.of(httpServletRequest.getRequestURI()))
                        .build())
                .retrieve()
                .bodyToFlux(ViewStats.class)
                .blockFirst()
                .getHits();

        EventFullDto eventFullDto = eventMapper.mapToFullDto(event);
        eventFullDto.setViews(views);
        return eventFullDto;
    }
}
