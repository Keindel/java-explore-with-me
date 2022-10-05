package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    //TODO JPA Criteria API
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

    Optional<Event> findByIdAndPublishedOnIsNotNull(Long id);

    /*
     * это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
     * текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
     * */
//    String text,
//    List<Long> categories,
//    Boolean paid,
//    LocalDateTime rangeStart, LocalDateTime rangeEnd,
//    Boolean onlyAvailable,
//    String stringSort,
//    Integer from, Integer size

    String confirmedRequestsCount = "(SELECT COUNT(part_req) FROM ParticipationRequest part_req " +
            "WHERE part_req.event = ev AND part_req.status = 'CONFIRMED')";

    //TODO JPA Criteria API
    @Query("SELECT ev FROM Event ev " +
            "WHERE (LOWER(ev.annotation) LIKE LOWER(CONCAT('%', :text, '%') ) " +
            "OR LOWER(ev.description) LIKE LOWER(concat('%', :text, '%') )) " +
            "AND (:categories IS NULL OR ev.category.id IN :categories) " +
            "AND (:paid IS NULL OR ev.paid = :paid) " +
            "AND (ev.eventDate BETWEEN :rangeStart AND :rangeEnd) " +
            "AND (:onlyAvailable IS NULL OR :onlyAvailable IS FALSE OR " +
            "ev.participantLimit = 0 OR " +
            "(ev.participantLimit < " + confirmedRequestsCount + " )) " +
            "ORDER BY ")
    Page<Event> findAllByAnnotationContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndPublishedOnIsNotNullAndEventDateIsBetween();
}
