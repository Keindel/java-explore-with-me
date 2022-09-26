package io.swagger.api;

import io.swagger.model.AdminUpdateEventRequest;
import io.swagger.model.ApiError;
import io.swagger.model.CategoryDto;
import io.swagger.model.CompilationDto;
import io.swagger.model.EventFullDto;
import io.swagger.model.NewCategoryDto;
import io.swagger.model.NewCompilationDto;
import io.swagger.model.NewUserRequest;
import io.swagger.model.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")
@RestController
public class AdminApiController implements AdminApi {

    private static final Logger log = LoggerFactory.getLogger(AdminApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AdminApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CategoryDto> addCategory(@Parameter(in = ParameterIn.DEFAULT, description = "данные добавляемой категории", required=true, schema=@Schema()) @Valid @RequestBody NewCategoryDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CategoryDto>(objectMapper.readValue("{\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n}", CategoryDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CategoryDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addEventToCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId,@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, description = "id пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCategory(@Parameter(in = ParameterIn.PATH, description = "id категории", required=true, schema=@Schema()) @PathVariable("catId") Long catId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<EventFullDto>> getEvents2(@Parameter(in = ParameterIn.QUERY, description = "список id пользователей, чьи события нужно найти" ,schema=@Schema()) @Valid @RequestParam(value = "users", required = false) List<Long> users,@Parameter(in = ParameterIn.QUERY, description = "список состояний в которых находятся искомые события" ,schema=@Schema()) @Valid @RequestParam(value = "states", required = false) List<String> states,@Parameter(in = ParameterIn.QUERY, description = "список id категорий в которых будет вестись поиск" ,schema=@Schema()) @Valid @RequestParam(value = "categories", required = false) List<Long> categories,@Parameter(in = ParameterIn.QUERY, description = "дата и время не раньше которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart,@Parameter(in = ParameterIn.QUERY, description = "дата и время не позже которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd,@Parameter(in = ParameterIn.QUERY, description = "количество событий, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from,@Parameter(in = ParameterIn.QUERY, description = "количество событий в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<EventFullDto>>(objectMapper.readValue("[ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}, {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<EventFullDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<EventFullDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<UserDto>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "id пользователей" ,schema=@Schema()) @Valid @RequestParam(value = "ids", required = false) List<Long> ids,@Parameter(in = ParameterIn.QUERY, description = "количество элементов, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from,@Parameter(in = ParameterIn.QUERY, description = "количество элементов в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<UserDto>>(objectMapper.readValue("[ {\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n}, {\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<UserDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> pin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> publishEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserDto> registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "данные добавляемого пользователя", required=true, schema=@Schema()) @Valid @RequestBody NewUserRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserDto>(objectMapper.readValue("{\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n}", UserDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> rejectEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> removeEventFromCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId,@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CompilationDto> saveCompilation(@Parameter(in = ParameterIn.DEFAULT, description = "данные новой подборки", required=true, schema=@Schema()) @Valid @RequestBody NewCompilationDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CompilationDto>(objectMapper.readValue("{\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n}", CompilationDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CompilationDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CompilationDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> unpin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CategoryDto> updateCategory(@Parameter(in = ParameterIn.DEFAULT, description = "Данные категории для изменения", required=true, schema=@Schema()) @Valid @RequestBody CategoryDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CategoryDto>(objectMapper.readValue("{\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n}", CategoryDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CategoryDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> updateEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId,@Parameter(in = ParameterIn.DEFAULT, description = "Данные для изменения информации о событии", required=true, schema=@Schema()) @Valid @RequestBody AdminUpdateEventRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

}
