package ru.practicum.explorewithme.api;

import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.ParticipationRequestDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<EventFullDto> addEvent(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId, @Parameter(in = ParameterIn.DEFAULT, description = "данные добавляемого события", required=true, schema=@Schema()) @Valid @RequestBody NewEventDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \\"Шоу летающей кукурузы\\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> addParticipationRequest(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@NotNull @Parameter(in = ParameterIn.QUERY, description = "id события" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "eventId", required = true) Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> cancelEvent(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id отменяемого события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \\"Шоу летающей кукурузы\\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> cancelParticipationRequest(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id события текущего пользователя", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId,@Parameter(in = ParameterIn.PATH, description = "id заявки, которую отменяет текущий пользователь", required=true, schema=@Schema()) @PathVariable("reqId") Long reqId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> cancelRequest(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id запроса на участие", required=true, schema=@Schema()) @PathVariable("requestId") Long requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> confirmParticipationRequest(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id события текущего пользователя", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId,@Parameter(in = ParameterIn.PATH, description = "id заявки, которую подтверждает текущий пользователь", required=true, schema=@Schema()) @PathVariable("reqId") Long reqId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> getEvent(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \\"Шоу летающей кукурузы\\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<ParticipationRequestDto>> getEventParticipants(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ParticipationRequestDto>>(objectMapper.readValue("[ {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}, {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<EventShortDto>> getEvents(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId, @Parameter(in = ParameterIn.QUERY, description = "количество элементов, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from, @Parameter(in = ParameterIn.QUERY, description = "количество элементов в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<EventShortDto>>(objectMapper.readValue("[ [ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 5,\n  \"eventDate\" : \"2024-03-10 14:30:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Фёдоров Матвей\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"views\" : 999\n}, {\n  \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 555,\n  \"eventDate\" : \"2025-09-13 21:00:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Паша Петров\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Концерт рок-группы 'Java Core'\",\n  \"views\" : 991\n} ], [ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 5,\n  \"eventDate\" : \"2024-03-10 14:30:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Фёдоров Матвей\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"views\" : 999\n}, {\n  \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 555,\n  \"eventDate\" : \"2025-09-13 21:00:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Паша Петров\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Концерт рок-группы 'Java Core'\",\n  \"views\" : 991\n} ] ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<EventShortDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<EventShortDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<ParticipationRequestDto>> getUserRequests(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ParticipationRequestDto>>(objectMapper.readValue("[ {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}, {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> updateEvent1(@Parameter(in = ParameterIn.PATH, description = "id текущего пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.DEFAULT, description = "Новые данные события", required=true, schema=@Schema()) @Valid @RequestBody UpdateEventRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \\"Шоу летающей кукурузы\\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

}
