package ru.practicum.explorewithme.repository;

import lombok.RequiredArgsConstructor;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.ViewStatsDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EndpointHitRepositoryCustomImpl implements EndpointHitRepositoryCustom {

    private final EntityManager entityManager;

    // TODO ask mentor
    /*@Override
    public List<ViewStatsDto> getViewStatsListByParamsCustom(LocalDateTime start,
                                                             LocalDateTime end,
                                                             List<String> uris,
                                                             Boolean unique) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EndpointHit> cqHit = cb.createQuery(EndpointHit.class);
        Root<EndpointHit> hitRoot = cqHit.from(EndpointHit.class);

        List<Predicate> predicates = new ArrayList<>();
        if (start != null && end != null) {
            Predicate predicateForRange = cb.between(hitRoot.get("timestamp"), start, end);
            predicates.add(predicateForRange);
        }
        if (uris != null && !uris.isEmpty()) {
            CriteriaBuilder.In<String> predicateForUris = cb.in(hitRoot.get("uri"));
            for (String uri : uris) {
                predicateForUris.value(uri);
            }
            predicates.add(predicateForUris);
        }
        Expression<Long> countHits = cb.count(hitRoot.get("ip"));
        if (Boolean.TRUE.equals(unique)) {
            countHits = cb.countDistinct(hitRoot.get("ip"));
        }

        CriteriaQuery<ViewStatsDto> cqViews = cb.createQuery(ViewStatsDto.class);
//        Root<ViewStats> viewsRoot = cqViews.from(ViewStats.class);

        CriteriaQuery<Long> cqLong = cb.createQuery(Long.class);
        cb.count(cqLong.from(EndpointHit.class));
        countHits = cb.count(cqLong.from(EndpointHit.class));

        CriteriaQuery<ViewStatsDto> q = cb.createQuery(ViewStatsDto.class);
        Root<EndpointHit> root = q.from(EndpointHit.class);

        Selection selectionUri = root.get("uri");
        q.select(cb.construct(ViewStatsDto.class, root.get("app"), root.get("uri"), countHits))
                .groupBy();

        List<ViewStatsDto> authors = entityManager.createQuery(q).getResultList();

        List<ViewStatsDto> viewStatsList = entityManager.createQuery(cqViews
                        .multiselect(cb.construct(ViewStatsDto.class, hitRoot.get("app"), hitRoot.get("uri"), countHits.alias("hits")))
                        .where(predicates.toArray(new Predicate[predicates.size()]))
                        .groupBy(hitRoot.get("uri"), hitRoot.get("app")))
                .getResultList();


//        CriteriaQuery<Object[]> cqObj = cb.createQuery(Object[].class);
//        Root<ViewStats> objRoot = cqViews.from(ViewStats.class);
//
//        List<Object[]> viewStatsList = entityManager.createQuery(cqObj
//                        .multiselect(hitRoot.get("app"), hitRoot.get("uri"), countHits.alias("hits"))
//                        .where(predicates.toArray(new Predicate[predicates.size()]))
//                        .groupBy(hitRoot.get("uri")))
//                .getResultList();

        return authors;
    }*/
}