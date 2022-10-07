package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.ParticipationRequestRepository;

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

    public EventFullDto mapToFullDto(Event event) {
        EventFullDto eventFullDto = modelMapper.map(event, EventFullDto.class);
        if (Boolean.FALSE.equals(event.getRequestModeration())) {
            eventFullDto.setConfirmedRequests(requestRepository.countPendingAndConfirmedByEvent(event));
        } else {
            eventFullDto.setConfirmedRequests(requestRepository.countByStatusAndEvent(Status.CONFIRMED, event));
        }
        return eventFullDto;
    }

    public EventShortDto mapToShortDto(Event event) {
        EventShortDto eventShortDto = modelMapper.map(event, EventShortDto.class);
        if (Boolean.FALSE.equals(event.getRequestModeration())) {
            eventShortDto.setConfirmedRequests(requestRepository.countPendingAndConfirmedByEvent(event));
        } else {
            eventShortDto.setConfirmedRequests(requestRepository.countByStatusAndEvent(Status.CONFIRMED, event));
        }
        return eventShortDto;
    }
}
