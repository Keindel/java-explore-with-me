package ru.practicum.explorewithme.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.repository.EventRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Page<Event> findAllByParamsCustom(List<Long> users,
                                             List<State> states,
                                             List<Long> categories,
                                             LocalDateTime rangeStart,
                                             LocalDateTime rangeEnd,
                                             Pageable page) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);
        Root<Event> eventRoot = cq.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();
        processListOfIdsToPredicate(users, cb, eventRoot, "initiator", predicates);
        processStatesToPredicates(states, cb, eventRoot, predicates);
        processListOfIdsToPredicate(categories, cb, eventRoot, "category", predicates);
        Predicate predicateForRanges = cb.between(eventRoot.get("eventDate"), rangeStart, rangeEnd);
        predicates.add(predicateForRanges);

        List<Event> eventList = entityManager.createQuery(cq
                        .select(eventRoot)
                        .where(predicates.toArray(new Predicate[predicates.size()])))
                .setFirstResult((page.getPageNumber()) * page.getPageSize())
                .setMaxResults(page.getPageSize())
                .getResultList();

        CriteriaQuery<Long> cqLong = cb.createQuery(Long.class);
        Long countTotal = entityManager.createQuery(cqLong.select(cb.count(cqLong.from(Event.class)))
                        .where(predicates.toArray(new Predicate[predicates.size()])))
                .getSingleResult();

        return new PageImpl<>(eventList, page, countTotal);
    }

    private static void processListOfIdsToPredicate(List<Long> users, CriteriaBuilder cb, Root<Event> eventRoot, String initiator, List<Predicate> predicates) {
        if (users != null) {
            CriteriaBuilder.In<Long> predicateForUsers = cb.in(eventRoot.get(initiator).get("id"));
            for (Long user : users) {
                predicateForUsers.value(user);
            }
            predicates.add(predicateForUsers);
        }
    }

    private static void processStatesToPredicates(List<State> states, CriteriaBuilder cb, Root<Event> eventRoot, List<Predicate> predicates) {
        if (states != null) {
            CriteriaBuilder.In<State> predicateForStates = cb.in(eventRoot.get("state"));
            for (State state : states) {
                predicateForStates.value(state);
            }
            predicates.add(predicateForStates);
        }
    }

    @Override
    public Page<Event> findAllByPublicParamsCustom(String text,
                                                   List<Long> categories,
                                                   Boolean paid,
                                                   LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                                   Boolean onlyAvailable,
                                                   Pageable page) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);
        Root<Event> eventRoot = cq.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();

        processTextToPredicates(text, cb, eventRoot, predicates);
        processListOfIdsToPredicate(categories, cb, eventRoot, "category", predicates);
        processPaidToPredicates(paid, cb, eventRoot, predicates);
        processAvailableToPredicates(onlyAvailable, cb, eventRoot, predicates);

        Predicate predicateForPublished = cb.isNotNull(eventRoot.get("publishedOn"));
        predicates.add(predicateForPublished);
        Predicate predicateForRanges = cb.between(eventRoot.get("eventDate"), rangeStart, rangeEnd);
        predicates.add(predicateForRanges);

        PageImpl<Event> pageResult;
        if (page.getSort().isSorted()) {
            pageResult = getPageResultWithSortingByDate(page, cb, cq, eventRoot, predicates);
        } else {
            pageResult = getAllEventsByPredicatesWithoutSorting(cq, eventRoot, predicates);
        }
        return pageResult;
    }

    private PageImpl<Event> getAllEventsByPredicatesWithoutSorting(CriteriaQuery<Event> cq, Root<Event> eventRoot, List<Predicate> predicates) {
        List<Event> eventList;
        PageImpl<Event> pageResult;
        eventList = entityManager.createQuery(cq
                        .select(eventRoot)
                        .where(predicates.toArray(new Predicate[predicates.size()]))
                )
                .getResultList();

        pageResult = new PageImpl<>(eventList);
        return pageResult;
    }

    private PageImpl<Event> getPageResultWithSortingByDate(Pageable page, CriteriaBuilder cb, CriteriaQuery<Event> cq, Root<Event> eventRoot, List<Predicate> predicates) {
        List<Event> eventList;
        PageImpl<Event> pageResult;
        eventList = entityManager.createQuery(cq
                        .select(eventRoot)
                        .where(predicates.toArray(new Predicate[predicates.size()])).orderBy(cb.desc(eventRoot.get("eventDate")))
                )
                .setFirstResult((page.getPageNumber()) * page.getPageSize())
                .setMaxResults(page.getPageSize())
                .getResultList();
        CriteriaQuery<Long> cqLong = cb.createQuery(Long.class);
        Long countTotal = entityManager.createQuery(cqLong.select(cb.count(cqLong.from(Event.class)))
                        .where(predicates.toArray(new Predicate[predicates.size()]))
                )
                .getSingleResult();

        pageResult = new PageImpl<>(eventList, page, countTotal);
        return pageResult;
    }

    private void processAvailableToPredicates(Boolean onlyAvailable, CriteriaBuilder cb, Root<Event> eventRoot, List<Predicate> predicates) {
        if (Boolean.TRUE.equals(onlyAvailable)) {
            CriteriaQuery<Long> cqLong = cb.createQuery(Long.class);
            Root<ParticipationRequest> reqRoot = cqLong.from(ParticipationRequest.class);
            Predicate predicateForReqEvent = cb.equal(reqRoot.get("event"), eventRoot);
            Predicate predicateForReqStatus = cb.equal(reqRoot.get("status"), Status.CONFIRMED);
            Predicate predicateForEventConfirmedRequests = cb.and(predicateForReqEvent, predicateForReqStatus);

            Long countConfirmed = entityManager.createQuery(cqLong.select(cb.count(reqRoot))
                            .where(predicateForEventConfirmedRequests))
                    .getSingleResult();

            Predicate predicateForNoLimit = cb.equal(eventRoot.get("participantLimit"), 0);
            Predicate predicateForLimitGtConfirmed = cb.gt(eventRoot.get("participantLimit"), countConfirmed);
            Predicate predicateForAvailable = cb.and(predicateForNoLimit, predicateForLimitGtConfirmed);

            predicates.add(predicateForAvailable);
        }
    }

    private static void processPaidToPredicates(Boolean paid, CriteriaBuilder cb, Root<Event> eventRoot, List<Predicate> predicates) {
        if (paid != null) {
            Predicate predicateForPaid = cb.equal(eventRoot.get("paid"), paid);
            predicates.add(predicateForPaid);
        }
    }

    private static void processTextToPredicates(String text, CriteriaBuilder cb, Root<Event> eventRoot, List<Predicate> predicates) {
        if (text != null) {
            Predicate predicateForTextAnnotation = cb.like(cb.lower(eventRoot.get("annotation")),
                    "%" + text.toLowerCase() + "%");
            Predicate predicateForTextDescription = cb.like(cb.lower(eventRoot.get("description")),
                    "%" + text.toLowerCase() + "%");
            Predicate predicateForText = cb.or(predicateForTextAnnotation, predicateForTextDescription);
            predicates.add(predicateForText);
        }
    }
}
