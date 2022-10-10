package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.EndpointHit;
import ru.practicum.explorewithme.model.EndpointHitDto;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.repository.EndpointHitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final EndpointHitRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public List<ViewStats> getViewStatsList(LocalDateTime start,
                                            LocalDateTime end,
                                            List<String> uris,
                                            Boolean unique) {
        if (Boolean.TRUE.equals(unique)) {
            return repository.getViewStatsListByParamsUnique(start, end, uris);
        }
        return repository.getViewStatsListByParams(start, end, uris);
    }

    @Override
    public EndpointHit addHit(EndpointHitDto hitDto) {
        return repository.save(modelMapper.map(hitDto, EndpointHit.class));
    }
}
