package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.AdminService;
import ru.practicum.explorewithme.util.ListModelMapper;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminApiController implements AdminApi {

    private final ObjectMapper objectMapper;

    private final ModelMapper modelMapper;

    private final ListModelMapper listModelMapper;

    private final HttpServletRequest request;

    private final AdminService adminService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        return new ResponseEntity<>(modelMapper.map(adminService
                .addCategory(newCategoryDto), CategoryDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/admin/categories")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(modelMapper.map(adminService
                .updateCategory(categoryDto), CategoryDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/categories/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("catId") Long catId) {
        return new ResponseEntity<>(adminService.deleteCategory(catId));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return new ResponseEntity<>(modelMapper.map(adminService.registerUser(newUserRequest), UserDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(adminService.deleteUser(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@Valid @RequestParam(value = "ids", required = false) List<Long> ids,
                                                  @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                  @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(listModelMapper.mapList(adminService.getUsers(ids, from, size), UserDto.class),
                HttpStatus.OK);
    }

    public ResponseEntity<List<EventFullDto>> getEventsDetailed(@Valid @RequestParam(value = "users", required = false) List<Long> users,
                                                                @Valid @RequestParam(value = "states", required = false) List<String> states,
                                                                @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
                                                                @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                                                @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                                                @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                                @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(listModelMapper.mapList(adminService.
                        getEventsDetailed(users, states, categories, rangeStart, rangeEnd, from, size),
                EventFullDto.class),
                HttpStatus.OK);
    }

    public ResponseEntity<EventFullDto> updateEvent(@PathVariable("eventId") Long eventId,
                                                    @Valid @RequestBody AdminUpdateEventRequest adminUpdateEventRequest) {
        return new ResponseEntity<>(modelMapper.map(adminService.updateEvent(eventId, adminUpdateEventRequest),
                EventFullDto.class),
                HttpStatus.OK);
    }

    public ResponseEntity<EventFullDto> publishEvent(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(adminService.publishEvent(eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    public ResponseEntity<EventFullDto> rejectEvent(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(adminService.rejectEvent(eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    public ResponseEntity<Void> removeEventFromCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required = true, schema = @Schema()) @PathVariable("compId") Long compId, @Parameter(in = ParameterIn.PATH, description = "id события", required = true, schema = @Schema()) @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CompilationDto> saveCompilation(@Parameter(in = ParameterIn.DEFAULT, description = "данные новой подборки", required = true, schema = @Schema()) @Valid @RequestBody NewCompilationDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CompilationDto>(objectMapper.readValue("{\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной ", CompilationDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CompilationDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CompilationDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> pin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required = true, schema = @Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> unpin(@Parameter(in = ParameterIn.PATH, description = "id подборки", required = true, schema = @Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCompilation(@Parameter(in = ParameterIn.PATH, description = "id подборки", required = true, schema = @Schema()) @PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}
