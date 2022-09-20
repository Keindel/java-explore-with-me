/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-19T14:46:57.997Z[GMT]")
@Validated
public interface AdminApi {

    @Operation(summary = "Добавление новой категории", description = "Обратите внимание: имя категории должно быть уникальным", tags={ "Admin: Категории" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Категория добавлена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<CategoryDto> addCategory(@Parameter(in = ParameterIn.DEFAULT, description = "данные добавляемой категории", required=true, schema=@Schema()) @Valid @RequestBody NewCategoryDto body);


    @Operation(summary = "Добавить событие в подборку", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие добавлено"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
        produces = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<Void> addEventToCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId, @Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId);


    @Operation(summary = "Удаление пользователя", description = "", tags={ "Admin: Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Пользователь удален"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/users/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, description = "id пользователя", required=true, schema=@Schema()) @PathVariable("userId") Long userId);


    @Operation(summary = "Удаление категории", description = "Обратите внимание: с категорией не должно быть связано ни одного события.", tags={ "Admin: Категории" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Категория удалена"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/categories/{catId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCategory(@Parameter(in = ParameterIn.PATH, description = "id категории", required=true, schema=@Schema()) @PathVariable("catId") Long catId);


    @Operation(summary = "Удаление подборки", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Подборка удалена"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations/{compId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId);


    @Operation(summary = "Поиск событий", description = "Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия", tags={ "Admin: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "События найдены", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventFullDto.class)))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/events",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<EventFullDto>> getEvents2(@Parameter(in = ParameterIn.QUERY, description = "список id пользователей, чьи события нужно найти" ,schema=@Schema()) @Valid @RequestParam(value = "users", required = false) List<Long> users, @Parameter(in = ParameterIn.QUERY, description = "список состояний в которых находятся искомые события" ,schema=@Schema()) @Valid @RequestParam(value = "states", required = false) List<String> states, @Parameter(in = ParameterIn.QUERY, description = "список id категорий в которых будет вестись поиск" ,schema=@Schema()) @Valid @RequestParam(value = "categories", required = false) List<Long> categories, @Parameter(in = ParameterIn.QUERY, description = "дата и время не раньше которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart, @Parameter(in = ParameterIn.QUERY, description = "дата и время не позже которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd, @Parameter(in = ParameterIn.QUERY, description = "количество событий, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from, @Parameter(in = ParameterIn.QUERY, description = "количество событий в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size);


    @Operation(summary = "Получение информации о пользователях", description = "Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки), либо о конкретных (учитываются указанные идентификаторы)", tags={ "Admin: Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Пользователи найдены", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "id пользователей" ,schema=@Schema()) @Valid @RequestParam(value = "ids", required = false) List<Long> ids, @Parameter(in = ParameterIn.QUERY, description = "количество элементов, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from, @Parameter(in = ParameterIn.QUERY, description = "количество элементов в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size);


    @Operation(summary = "Закрепить подборку на главной странице", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Подборка закреплена"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations/{compId}/pin",
        produces = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<Void> pin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId);


    @Operation(summary = "Публикация события", description = "Обратите внимание:  - дата начала события должна быть не ранее чем за час от даты публикации. - событие должно быть в состоянии ожидания публикации", tags={ "Admin: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие опубликовано", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventFullDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/events/{eventId}/publish",
        produces = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<EventFullDto> publishEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId);


    @Operation(summary = "Добавление нового пользователя", description = "", tags={ "Admin: Пользователи" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/users",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<UserDto> registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "данные добавляемого пользователя", required=true, schema=@Schema()) @Valid @RequestBody NewUserRequest body);


    @Operation(summary = "Отклонение события", description = "Обратите внимание: событие не должно быть опубликовано.", tags={ "Admin: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие отклонено", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventFullDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/events/{eventId}/reject",
        produces = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<EventFullDto> rejectEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId);


    @Operation(summary = "Удалить событие из подборки", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие удалено из подборки"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> removeEventFromCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId, @Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId);


    @Operation(summary = "Добавление новой подборки", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Подборка добавлена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompilationDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<CompilationDto> saveCompilation(@Parameter(in = ParameterIn.DEFAULT, description = "данные новой подборки", required=true, schema=@Schema()) @Valid @RequestBody NewCompilationDto body);


    @Operation(summary = "Открепить подборку на главной странице", description = "", tags={ "Admin: Подборки событий" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Подборка откреплена"),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/compilations/{compId}/pin",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> unpin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required=true, schema=@Schema()) @PathVariable("compId") Long compId);


    @Operation(summary = "Изменение категории", description = "Обратите внимание: имя категории должно быть уникальным", tags={ "Admin: Категории" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Данные категории изменены", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<CategoryDto> updateCategory(@Parameter(in = ParameterIn.DEFAULT, description = "Данные категории для изменения", required=true, schema=@Schema()) @Valid @RequestBody CategoryDto body);


    @Operation(summary = "Редактирование события", description = "Редактирование данных любого события администратором. Валидация данных не требуется.", tags={ "Admin: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие отредактировано", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventFullDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/admin/events/{eventId}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<EventFullDto> updateEvent(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("eventId") Long eventId, @Parameter(in = ParameterIn.DEFAULT, description = "Данные для изменения информации о событии", required=true, schema=@Schema()) @Valid @RequestBody AdminUpdateEventRequest body);

}

