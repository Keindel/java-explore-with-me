package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.event.Event;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public List<Event> getEventsShort(String text,
                                      List<Long> categories,
                                      Boolean paid,
                                      String rangeStart,
                                      String rangeEnd,
                                      Boolean onlyAvailable,
                                      String sort,
                                      Integer from,
                                      Integer size) {
        return null;
    }

    @Override
    public Event getEventById(Long id) {
        return null;
    }
}
