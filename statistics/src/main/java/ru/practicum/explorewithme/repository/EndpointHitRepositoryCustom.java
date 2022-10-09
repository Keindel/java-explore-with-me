package ru.practicum.explorewithme.repository;

import ru.practicum.explorewithme.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitRepositoryCustom {

    List<ViewStats> getViewStatsListByParams(LocalDateTime start,
                                             LocalDateTime end,
                                             List<String> uris,
                                             Boolean unique);
}
