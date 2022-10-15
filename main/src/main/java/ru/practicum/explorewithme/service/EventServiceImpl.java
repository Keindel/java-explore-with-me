package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.ViewStatsDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.statsclient.StatsHitter;
import ru.practicum.explorewithme.statsclient.UriListMaker;
import ru.practicum.explorewithme.statsclient.ViewsStatsRetriever;
import ru.practicum.explorewithme.util.CustomPageable;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final UriListMaker uriListMaker;

    private final ViewsStatsRetriever viewsStatsRetriever;

    private final StatsHitter statsHitter;

    @Override
    public List<EventShortDto> getEventsShort(String text,
                                              List<Long> categories,
                                              Boolean paid,
                                              LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                              Boolean onlyAvailable,
                                              String stringSort,
                                              Integer from, Integer size,
                                              HttpServletRequest httpServletRequest) throws EventTimeException {
        statsHitter.postHitToStats(httpServletRequest);

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

        List<String> uriList = uriListMaker.make(eventList);
        List<ViewStatsDto> viewsList = viewsStatsRetriever.retrieveViewsList(uriList);
        List<EventShortDto> eventShortDtoList = eventMapper.mapToEventShortDtoList(eventList, viewsList);

        // if page is unsorted - sort by views
        if (page.getSort().isUnsorted()) {
            eventShortDtoList = eventShortDtoList.stream()
                    .sorted(Comparator.comparingLong(EventShortDto::getViews).reversed())
                    .skip(from)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        return eventShortDtoList;
    }

    @Override
    public EventFullDto getEventById(Long id, HttpServletRequest httpServletRequest)
            throws EventNotFoundException {
        Event event = eventRepository.findByIdAndPublishedOnIsNotNull(id).orElseThrow(()
                -> new EventNotFoundException(id));

        statsHitter.postHitToStats(httpServletRequest);

        Long views = viewsStatsRetriever.retrieveHitsForEvent(List.of(httpServletRequest.getRequestURI()), event);
        return eventMapper.mapToFullDto(event, views);
    }
}
