package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.EndpointHitDto;
import ru.practicum.explorewithme.model.ViewStatsDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final WebClient statsClient;

    private final EventMapper eventMapper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<EventShortDto> getEventsShort(String text,
                                              List<Long> categories,
                                              Boolean paid,
                                              LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                              Boolean onlyAvailable,
                                              String stringSort,
                                              Integer from, Integer size,
                                              HttpServletRequest httpServletRequest) throws EventTimeException {
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
                eventList = eventRepository.findAllByPublicParamsCustom(text, categories, paid, rangeStart, rangeEnd,
                        onlyAvailable, page).getContent();
                break;
            case "VIEWS":
                page = CustomPageable.of(from, size);
                eventList = eventRepository.findAllByPublicParamsCustom(text, categories, paid, rangeStart, rangeEnd,
                        onlyAvailable, page).getContent();
                break;
            default:
                throw new IllegalArgumentException(stringSort + " - not supported");
        }

        List<String> uriList = new ArrayList<>();
        eventList.forEach(event -> uriList.add("/events/" + event.getId()));

        List<ViewStatsDto> viewsList = getViewsList(uriList, rangeStart, rangeEnd);
        List<EventShortDto> eventShortDtoList = getEventShortDtoList(eventList, viewsList);

        if (page.getSort().isUnsorted()) {
            eventShortDtoList = eventShortDtoList.stream()
                    .sorted(Comparator.comparingLong(EventShortDto::getViews).reversed())
                    .skip(from)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        return eventShortDtoList;
    }


    //TODO ask mentor
    // реализация все за один запрос в Stats-server, чтобы не было roundup-ов на каждый event -
    // забрать все нужные вьюшки сразу
    // учесть, что для некоторых ивентов может не быть записей о просмотрах
    private List<EventShortDto> getEventShortDtoList(List<Event> eventList, List<ViewStatsDto> viewsList) {
        Map<Long, Long> eventIdToHitsMap = new HashMap<>();
        for (ViewStatsDto viewStatsDto : viewsList) {
            String[] uriSplit = viewStatsDto.getUri().split("/");
            int uriSplitLen = uriSplit.length;
            Long eventId = Long.parseLong(uriSplit[uriSplitLen - 1]);
            eventIdToHitsMap.put(eventId, viewStatsDto.getHits());
        }
        return eventList.stream()
                .map(event -> eventMapper.mapToShortDto(event,
                        eventIdToHitsMap.getOrDefault(event.getId(), 0L)))
                .collect(Collectors.toList());
    }

    private void postHitToStats(HttpServletRequest request) {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHitDto("main-server",
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        LocalDateTime.now()))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public EventFullDto getEventById(Long id, HttpServletRequest httpServletRequest)
            throws EventNotFoundException {
        Event event = eventRepository.findByIdAndPublishedOnIsNotNull(id).orElseThrow(()
                -> new EventNotFoundException(id));

        postHitToStats(httpServletRequest);

        Long views = getHitsForEvent(List.of(httpServletRequest.getRequestURI()), event);
        return eventMapper.mapToFullDto(event, views);
    }

    private Long getHitsForEvent(List<String> uris, Event event) {
        return statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("start", event.getCreatedOn()
                                .format(dateTimeFormatter))
                        .queryParam("end", LocalDateTime.now()
                                .format(dateTimeFormatter))
                        .queryParam("uris", uris)
                        .build())
                .retrieve()
                .bodyToFlux(ViewStatsDto.class)
                .blockFirst()
                .getHits();
    }

    private List<ViewStatsDto> getViewsList(List<String> uris, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        return statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("start", rangeStart
                                .format(dateTimeFormatter))
                        .queryParam("end", rangeEnd
                                .format(dateTimeFormatter))
                        .queryParam("uris", uris)
                        .build())
                .retrieve()
                .bodyToFlux(ViewStatsDto.class)
                .collect(Collectors.toList())
                .block();
    }
}
