package ru.practicum.explorewithme.api;

import ru.practicum.explorewithme.model.EventFullDto;
import ru.practicum.explorewithme.model.EventShortDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")
@RestController
public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<EventFullDto> getEvent1(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.
                        readValue(" ", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<EventShortDto>> getEvents1(@Parameter(in = ParameterIn.QUERY, description = "текст для поиска в содержимом аннотации и подробном описании события" ,schema=@Schema()) @Valid @RequestParam(value = "text", required = false) String text, @Parameter(in = ParameterIn.QUERY, description = "список идентификаторов категорий в которых будет вестись поиск" ,schema=@Schema()) @Valid @RequestParam(value = "categories", required = false) List<Long> categories, @Parameter(in = ParameterIn.QUERY, description = "поиск только платных/бесплатных событий" ,schema=@Schema()) @Valid @RequestParam(value = "paid", required = false) Boolean paid, @Parameter(in = ParameterIn.QUERY, description = "дата и время не раньше которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart, @Parameter(in = ParameterIn.QUERY, description = "дата и время не позже которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd, @Parameter(in = ParameterIn.QUERY, description = "только события у которых не исчерпан лимит запросов на участие" ,schema=@Schema( defaultValue="false")) @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue="false") Boolean onlyAvailable, @Parameter(in = ParameterIn.QUERY, description = "Вариант сортировки: по дате события или по количеству просмотров" ,schema=@Schema(allowableValues={ "EVENT_DATE", "VIEWS" }
)) @Valid @RequestParam(value = "sort", required = false) String sort, @Parameter(in = ParameterIn.QUERY, description = "количество событий, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from, @Parameter(in = ParameterIn.QUERY, description = "количество событий в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<EventShortDto>>(objectMapper.
                        readValue(" ", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<EventShortDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<EventShortDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
