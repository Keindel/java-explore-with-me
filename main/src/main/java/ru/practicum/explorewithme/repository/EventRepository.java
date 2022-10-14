package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.model.event.Event;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

    Page<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    Optional<Event> findByIdAndPublishedOnIsNotNull(Long id);

    @Query("SELECT ev FROM Event as ev " +
            "WHERE function('distance', :lat1, :lon1, ev.location.lat, ev.location.lon) <= :radiusInKm")
    List<Event> findAllByAreaParams(BigDecimal lat1, BigDecimal lon1, Integer radiusInKm);
}
