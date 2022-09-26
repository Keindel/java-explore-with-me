package io.swagger.api;

import io.swagger.model.ApiError;
import io.swagger.model.CompilationDto;
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
public class CompilationsApiController implements CompilationsApi {

    private static final Logger log = LoggerFactory.getLogger(CompilationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CompilationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CompilationDto> getCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId) {
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

    public ResponseEntity<List<CompilationDto>> getCompilations(@Parameter(in = ParameterIn.QUERY, description = "искать только закрепленные/не закрепленные подборки" ,schema=@Schema()) @Valid @RequestParam(value = "pinned", required = false) Boolean pinned,@Parameter(in = ParameterIn.QUERY, description = "количество элементов, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from,@Parameter(in = ParameterIn.QUERY, description = "количество элементов в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<CompilationDto>>(objectMapper.readValue("[ {\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n}, {\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<CompilationDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<CompilationDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
