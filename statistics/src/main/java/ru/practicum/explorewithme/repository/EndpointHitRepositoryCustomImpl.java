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
public class EndpointHitRepositoryCustomImpl implements EndpointHitRepositoryCustom{

    private final EntityManager entityManager;

    @Override
    public List<ViewStats> getViewStatsListByParams(LocalDateTime start,
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
        CriteriaQuery<ViewStats> cqViews = cb.createQuery(ViewStats.class);
        List<ViewStats> viewStatsList = entityManager.createQuery(cqViews
                        .multiselect(hitRoot.get("app"), hitRoot.get("uri"), countHits.alias("hits"))
                        .where(predicates.toArray(new Predicate[predicates.size()]))
                        .groupBy(hitRoot.get("uri")))
                .getResultList();

        return viewStatsList;
    }
}
