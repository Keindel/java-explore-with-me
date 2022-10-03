package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    @Query("SELECT ev FROM Event ev " +
            "WHERE (:users IS NULL OR ev.initiator.id IN :users) " +
            "AND (:states IS NULL OR ev.state IN :states)" +
            "AND (:categories IS NULL OR ev.category.id IN :categories) " +
            "AND ev.eventDate BETWEEN :rangeStart AND :rangeEnd")
    Page<Event> findAllByParams(List<Long> users,
                                List<State> states,
                                List<Long> categories,
                                LocalDateTime rangeStart,
                                LocalDateTime rangeEnd,
                                Pageable page);
}
