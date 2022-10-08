package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryCustom {

    Page<Event> findAllByParamsCustom(List<Long> users,
                                List<State> states,
                                List<Long> categories,
                                LocalDateTime rangeStart,
                                LocalDateTime rangeEnd,
                                Pageable page);

    Page<Event> findAllByPublicParamsCustom(String text,
                                                   List<Long> categories,
                                                   Boolean paid,
                                                   LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                                   Boolean onlyAvailable,
                                                   Pageable page);
}
