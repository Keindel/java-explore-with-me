package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    
    List<Event> getEventsShort(String text,
                               List<Long> categories,
                               Boolean paid,
                               LocalDateTime rangeStart, LocalDateTime rangeEnd,
                               Boolean onlyAvailable,
                               String sort,
                               Integer from, Integer size) throws EventTimeException;

    EventFullDto getEventById(Long id) throws EventNotFoundException, EventTimeException;
}
