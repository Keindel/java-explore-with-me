package ru.practicum.explorewithme.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Value("${statistics-server.url}")
    private String url;

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
