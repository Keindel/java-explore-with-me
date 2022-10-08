package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.event.Event;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

    Page<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    Optional<Event> findByIdAndPublishedOnIsNotNull(Long id);
}
