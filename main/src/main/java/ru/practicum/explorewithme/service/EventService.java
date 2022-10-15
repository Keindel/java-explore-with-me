package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventShortDto> getEventsShort(String text,
                                       List<Long> categories,
                                       Boolean paid,
                                       LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                       Boolean onlyAvailable,
                                       String sort,
                                       Integer from, Integer size,
                                       HttpServletRequest httpServletRequest) throws EventTimeException;

    EventFullDto getEventById(Long id, HttpServletRequest httpServletRequest)
            throws EventNotFoundException, EventTimeException;
}
