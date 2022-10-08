package ru.practicum.explorewithme.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
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
        if (users != null) {
            CriteriaBuilder.In<Long> predicateForUsers = cb.in(eventRoot.get("initiator").get("id"));
            for (Long user : users) {
                predicateForUsers.value(user);
            }
            predicates.add(predicateForUsers);
        }
        if (states != null) {
            CriteriaBuilder.In<State> predicateForStates = cb.in(eventRoot.get("state"));
            System.out.println("IM HERE");
            for (State state : states) {
                predicateForStates.value(state);
            }
            predicates.add(predicateForStates);
        }
        if (categories != null) {
            CriteriaBuilder.In<Long> predicateForCategories = cb.in(eventRoot.get("category").get("id"));
            for (Long category : categories) {
                predicateForCategories.value(category);
            }
            predicates.add(predicateForCategories);
        }
        Predicate predicateForRanges = cb.between(eventRoot.get("eventDate"), rangeStart, rangeEnd);
        predicates.add(predicateForRanges);

        List<Event> eventList = entityManager.createQuery(cq
                                .select(eventRoot)
                                .where(predicates.toArray(new Predicate[predicates.size()]))
//                        .orderBy()
                )
                .getResultList();
        PageImpl<Event> page1 = new PageImpl<>(eventList);
        return page1;
    }
}
