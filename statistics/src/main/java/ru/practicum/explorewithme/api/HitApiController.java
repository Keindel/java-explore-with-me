package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.EndpointHit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.model.EndpointHitDto;
import ru.practicum.explorewithme.service.StatisticsService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
public class HitApiController implements HitApi {

    private final StatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<EndpointHit> hit(@Valid @RequestBody EndpointHitDto endpointHitDto) {
        return new ResponseEntity<>(statisticsService.addHit(endpointHitDto),
                HttpStatus.OK);
    }
}
