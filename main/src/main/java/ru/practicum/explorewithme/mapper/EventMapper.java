package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.model.ViewStatsDto;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.ParticipationRequestRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final ParticipationRequestRepository requestRepository;

    private Converter<Long, Category> convertLongToCat = src -> {
        try {
            return src.getSource() == null ? null : this.findCategory(src.getSource());
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    };

    public <T> Event mapToEvent(T eventSomeDto, Long id) {
        modelMapper.typeMap(NewEventDto.class, Event.class).addMappings(m
                -> m.using(convertLongToCat).map(NewEventDto::getCategory, Event::setCategory));
        Event event = modelMapper.map(eventSomeDto, Event.class);
        if (id != null) {
            event.setId(id);
        }
        return event;
    }

    public <T> Event mapFromUpdateToEvent(T eventSomeDto, Event event) {
        modelMapper.typeMap(UpdateEventRequest.class, Event.class).addMappings(m
                -> m.using(convertLongToCat).map(UpdateEventRequest::getCategory, Event::setCategory));
        modelMapper.typeMap(AdminUpdateEventRequest.class, Event.class).addMappings(m
                -> m.using(convertLongToCat).map(AdminUpdateEventRequest::getCategory, Event::setCategory));
        modelMapper.map(eventSomeDto, event);
        return event;
    }

    private Category findCategory(Long catId) throws CategoryNotFoundException {
        return categoryRepository.findById(catId).orElseThrow(() -> new CategoryNotFoundException(catId));
    }

    public EventFullDto mapToFullDto(Event event, Long views) {
        EventFullDto eventFullDto = modelMapper.map(event, EventFullDto.class);
        if (Boolean.FALSE.equals(event.getRequestModeration())) {
            eventFullDto.setConfirmedRequests(requestRepository.countPendingAndConfirmedByEvent(event));
        } else {
            eventFullDto.setConfirmedRequests(requestRepository.countByStatusAndEvent(Status.CONFIRMED, event));
        }
        eventFullDto.setViews(views);
        return eventFullDto;
    }

    public EventShortDto mapToShortDto(Event event, Long views) {
        EventShortDto eventShortDto = modelMapper.map(event, EventShortDto.class);
        if (Boolean.FALSE.equals(event.getRequestModeration())
                || event.getParticipantLimit() == 0) {
            // в этих случаях подтверждение реквеста не требуется, поэтому считаем все реквесты
            eventShortDto.setConfirmedRequests(requestRepository.countPendingAndConfirmedByEvent(event));
        } else {
            eventShortDto.setConfirmedRequests(requestRepository.countByStatusAndEvent(Status.CONFIRMED, event));
        }
        eventShortDto.setViews(views);
        return eventShortDto;
    }

    //TODO ask mentor
    /*
     реализация все за один запрос в Stats-server, чтобы не было roundup-ов на каждый Event -
     забираются все нужные ViewStats сразу
     + учитывается, что для некоторых Event может не быть записей о просмотрах
     */
    public List<EventShortDto> mapToEventShortDtoList(List<Event> eventList, List<ViewStatsDto> viewsList) {
        Map<Long, Long> eventIdToHitsMap = getEventIdToHitsMap(viewsList);
        return eventList.stream()
                .map(event -> this.mapToShortDto(event,
                        eventIdToHitsMap.getOrDefault(event.getId(), 0L)))
                .collect(Collectors.toList());
    }

    private static Map<Long, Long> getEventIdToHitsMap(List<ViewStatsDto> viewsList) {
        Map<Long, Long> eventIdToHitsMap = new HashMap<>();
        for (ViewStatsDto viewStatsDto : viewsList) {
            String[] uriSplit = viewStatsDto.getUri().split("/");
            int uriSplitLen = uriSplit.length;
            Long eventId = Long.parseLong(uriSplit[uriSplitLen - 1]);
            eventIdToHitsMap.put(eventId, viewStatsDto.getHits());
        }
        return eventIdToHitsMap;
    }

    public List<EventFullDto> mapToEventFullDtoList(List<Event> eventList, List<ViewStatsDto> viewsList) {
        Map<Long, Long> eventIdToHitsMap = getEventIdToHitsMap(viewsList);
        return eventList.stream()
                .map(event -> this.mapToFullDto(event,
                        eventIdToHitsMap.getOrDefault(event.getId(), 0L)))
                .collect(Collectors.toList());
    }
}
