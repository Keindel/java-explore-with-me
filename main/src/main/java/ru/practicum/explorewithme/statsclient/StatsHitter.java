package ru.practicum.explorewithme.statsclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.model.EndpointHitDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StatsHitter {

    private final WebClient statsClient;

    public void postHitToStats(HttpServletRequest request) {
        statsClient.post()
                .uri("/hit")
                .bodyValue(new EndpointHitDto("main-server",
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        LocalDateTime.now()))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
