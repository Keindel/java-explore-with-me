package ru.practicum.explorewithme.statsclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.model.ViewStatsDto;
import ru.practicum.explorewithme.model.event.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ViewsStatsRetriever {

    private final WebClient statsClient;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime STATS_SERVER_BORN = LocalDateTime.of(2022, 1, 1, 0, 0);

    public Long retrieveHitsForEvent(List<String> uris, Event event) {
        ViewStatsDto viewStatsDto = statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("start", event.getCreatedOn()
                                .format(dateTimeFormatter))
                        .queryParam("end", LocalDateTime.now()
                                .format(dateTimeFormatter))
                        .queryParam("uris", uris)
                        .build())
                .retrieve()
                .bodyToFlux(ViewStatsDto.class)
                .blockFirst();
        if (viewStatsDto == null) {
            return 0L;
        }
        return viewStatsDto.getHits();
    }

    public List<ViewStatsDto> retrieveViewsList(List<String> uris) {
        LocalDateTime statsDateStart = STATS_SERVER_BORN;
        LocalDateTime statsDateEnd = LocalDateTime.now();
        return statsClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("start", statsDateStart
                                .format(dateTimeFormatter))
                        .queryParam("end", statsDateEnd
                                .format(dateTimeFormatter))
                        .queryParam("uris", uris)
                        .build())
                .retrieve()
                .bodyToFlux(ViewStatsDto.class)
                .collect(Collectors.toList())
                .block();
    }
}
