package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.model.event.Event;

import java.util.List;

public interface EventService {
    
    List<Event> getEventsShort(String text, List<Long> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, String sort, Integer from, Integer size);

    Event getEventById(Long id);
}
