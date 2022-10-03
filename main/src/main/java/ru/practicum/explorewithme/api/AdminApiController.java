package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.AdminService;
import ru.practicum.explorewithme.mapper.ListModelMapper;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminApiController implements AdminApi {

    private final ModelMapper modelMapper;

    private final ListModelMapper listModelMapper;

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

    @GetMapping("/events")
    public ResponseEntity<List<EventFullDto>> getEventsDetailed(@Valid @RequestParam(value = "users", required = false) List<Long> users,
                                                                @Valid @RequestParam(value = "states", required = false) List<State> states,
                                                                @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
                                                                @Valid @RequestParam(value = "rangeStart", required = false)
                                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                                @Valid @RequestParam(value = "rangeEnd", required = false)
                                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                                @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                                @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(listModelMapper.mapList(adminService.
                        getEventsDetailed(users, states, categories, rangeStart, rangeEnd, from, size),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable("eventId") Long eventId,
                                                    @Valid @RequestBody AdminUpdateEventRequest adminUpdateEventRequest) {
        return new ResponseEntity<>(modelMapper.map(adminService.updateEvent(eventId, adminUpdateEventRequest),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/events/{eventId}/publish")
    public ResponseEntity<EventFullDto> publishEvent(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(adminService.publishEvent(eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/events/{eventId}/reject")
    public ResponseEntity<EventFullDto> rejectEvent(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(modelMapper.map(adminService.rejectEvent(eventId),
                EventFullDto.class),
                HttpStatus.OK);
    }

    @PostMapping("/compilations")
    public ResponseEntity<CompilationDto> saveCompilation(@Valid @RequestBody NewCompilationDto newCompilationDto) throws EventNotFoundException {
        return new ResponseEntity<>(modelMapper.map(adminService.saveCompilation(newCompilationDto),
                CompilationDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/compilations/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable("compId") Long compId) {
        return new ResponseEntity<>(adminService.deleteCompilation(compId));
    }

    @DeleteMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> removeEventFromCompilation(@PathVariable("compId") Long compId,
                                                           @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(adminService.removeEventFromCompilation(compId, eventId));
    }

    @PatchMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> addEventToCompilation(@PathVariable("compId") Long compId,
                                                      @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(adminService.addEventToCompilation(compId, eventId));
    }

    @DeleteMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> unpinCompilation(@PathVariable("compId") Long compId) {
        return new ResponseEntity<>(adminService.unpinCompilation(compId));
    }

    @PatchMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> pinCompilation(@PathVariable("compId") Long compId) {
        return new ResponseEntity<>(adminService.pinCompilation(compId));
    }
}
