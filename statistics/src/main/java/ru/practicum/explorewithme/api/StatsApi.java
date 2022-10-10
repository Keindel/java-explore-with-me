/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.practicum.explorewithme.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.explorewithme.model.ViewStats;
import ru.practicum.explorewithme.model.ViewStatsDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface StatsApi {

    @RequestMapping(value = "/stats",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ViewStats>> getStats(@NotNull @Valid @RequestParam(value = "start", required = true) LocalDateTime start,
                                             @NotNull @Valid @RequestParam(value = "end", required = true) LocalDateTime end,
                                             @Valid @RequestParam(value = "uris", required = false) List<String> uris,
                                             @Valid @RequestParam(value = "unique", required = false, defaultValue="false") Boolean unique);

}

