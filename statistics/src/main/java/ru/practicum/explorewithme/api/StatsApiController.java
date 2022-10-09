package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.ViewStatsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.service.StatisticsService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsApiController implements StatsApi {

    private final StatisticsService statisticsService;

    public ResponseEntity<List<ViewStats>> getStats(@NotNull @Valid @RequestParam(value = "start", required = true)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                    @NotNull @Valid @RequestParam(value = "end", required = true)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                    @Valid @RequestParam(value = "uris", required = false) List<String> uris,
                                                    @Valid @RequestParam(value = "unique", required = false, defaultValue = "false") Boolean unique) {
        return new ResponseEntity<>(statisticsService.getViewStatsList(start, end, uris, unique),
                HttpStatus.OK);
    }
}
