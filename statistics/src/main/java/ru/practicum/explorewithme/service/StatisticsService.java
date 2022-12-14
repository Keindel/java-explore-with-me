package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.EndpointHitDto;
import ru.practicum.explorewithme.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {

    List<ViewStats> getViewStatsList(LocalDateTime start,
                                        LocalDateTime end,
                                        List<String> uris,
                                        Boolean unique);

    EndpointHit addHit(EndpointHitDto endpointHitDto);
}
