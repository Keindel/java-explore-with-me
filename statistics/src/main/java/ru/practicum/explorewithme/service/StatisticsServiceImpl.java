package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.repository.EndpointHitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final EndpointHitRepository repository;

    @Override
    public ViewStats getViewStats() {
        return null;
    }

    @Override
    public List<ViewStats> getViewStatsList(LocalDateTime start,
                                            LocalDateTime end,
                                            List<String> uris,
                                            Boolean unique) {
        return repository.findAll();
    }

    @Override
    public EndpointHit addHit(EndpointHit hit) {
        return repository.save(hit);
    }
}
