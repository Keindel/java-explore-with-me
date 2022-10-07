package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.repository.CategoryRepository;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

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
}
