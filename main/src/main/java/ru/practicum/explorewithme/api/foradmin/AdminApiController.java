package ru.practicum.explorewithme.api.foradmin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.LocationAreaNotFoundException;
import ru.practicum.explorewithme.mapper.CompilationMapper;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.mapper.ListModelMapper;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.location.LocationArea;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.UserDto;
import ru.practicum.explorewithme.service.AdminService;
import ru.practicum.explorewithme.statsclient.UriListMaker;
import ru.practicum.explorewithme.statsclient.ViewsStatsRetriever;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminApiController implements AdminApi {

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    private final CompilationMapper compilationMapper;

    private final ListModelMapper listModelMapper;

    private final AdminService adminService;

    private final UriListMaker uriListMaker;

    private final ViewsStatsRetriever viewsStatsRetriever;

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        return new ResponseEntity<>(modelMapper.map(adminService
                .addCategory(newCategoryDto), CategoryDto.class),
                HttpStatus.OK);
    }

    @PatchMapping("/categories")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(modelMapper.map(adminService
                .updateCategory(categoryDto), CategoryDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/categories/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("catId") Long catId) {
        adminService.deleteCategory(catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return new ResponseEntity<>(modelMapper.map(adminService.registerUser(newUserRequest), UserDto.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        adminService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
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
                                                                @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size)
            throws EventTimeException {

        List<Event> eventList = adminService
                .getEventsDetailed(users, states, categories, rangeStart, rangeEnd, from, size);
        return new ResponseEntity<>(eventMapper.mapToEventFullDtoList(eventList,
                viewsStatsRetriever.retrieveViewsList(uriListMaker.make(eventList))),
                HttpStatus.OK);
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable("eventId") Long eventId,
                                                    @Valid @RequestBody AdminUpdateEventRequest adminUpdateEventRequest)
            throws RequestLogicException, EventNotFoundException {

        Event event = adminService.updateEvent(eventId, adminUpdateEventRequest);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    @PatchMapping("/events/{eventId}/publish")
    public ResponseEntity<EventFullDto> publishEvent(@PathVariable("eventId") Long eventId)
            throws EventTimeException, ForbiddenException, EventNotFoundException {

        Event event = adminService.publishEvent(eventId);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    private EventFullDto composeEventFullDto(Event event) {
        return eventMapper.mapToFullDto(event,
                viewsStatsRetriever.retrieveHitsForEvent(uriListMaker.make(List.of(event)), event));
    }

    @PatchMapping("/events/{eventId}/reject")
    public ResponseEntity<EventFullDto> rejectEvent(@PathVariable("eventId") Long eventId)
            throws ForbiddenException, EventNotFoundException {

        Event event = adminService.rejectEvent(eventId);
        return new ResponseEntity<>(composeEventFullDto(event),
                HttpStatus.OK);
    }

    @PostMapping("/compilations")
    public ResponseEntity<CompilationDto> saveCompilation(@Valid @RequestBody NewCompilationDto newCompilationDto)
            throws EventNotFoundException {
        return new ResponseEntity<>(compilationMapper.mapToDto(adminService.saveCompilation(newCompilationDto)),
                HttpStatus.OK);
    }

    @DeleteMapping("/compilations/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable("compId") Long compId) {
        adminService.deleteCompilation(compId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> removeEventFromCompilation(@PathVariable("compId") Long compId,
                                                           @PathVariable("eventId") Long eventId)
            throws CompilationNotFoundException, EventNotFoundException {
        adminService.removeEventFromCompilation(compId, eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> addEventToCompilation(@PathVariable("compId") Long compId,
                                                      @PathVariable("eventId") Long eventId)
            throws CompilationNotFoundException, EventNotFoundException {
        adminService.addEventToCompilation(compId, eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> unpinCompilation(@PathVariable("compId") Long compId)
            throws CompilationNotFoundException {
        adminService.unpinCompilation(compId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> pinCompilation(@PathVariable("compId") Long compId)
            throws CompilationNotFoundException {
        adminService.pinCompilation(compId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/areas")
    public ResponseEntity<LocationArea> addArea(@Valid @RequestBody LocationArea locationArea) {
        return new ResponseEntity<>(adminService.addArea(locationArea),
                HttpStatus.OK);
    }

    @DeleteMapping("/areas/{areaId}")
    public ResponseEntity<Void> addArea(@PathVariable Long areaId) {
        adminService.deleteArea(areaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/areas")
    public ResponseEntity<List<LocationArea>> getAreas() {
        return new ResponseEntity<>(adminService.getAreas(),
                HttpStatus.OK);
    }

    @GetMapping("/areas/{areaId}/events")
    public ResponseEntity<List<EventShortDto>> getEventsInArea(@PathVariable Long areaId,
                                                               @RequestParam(required = false, defaultValue = "0") Integer from,
                                                               @RequestParam(required = false, defaultValue = "10") Integer size)
            throws LocationAreaNotFoundException {

        List<Event> eventList = adminService.getEventsInArea(areaId, from, size);
        return new ResponseEntity<>(eventMapper.mapToEventShortDtoList(eventList,
                viewsStatsRetriever.retrieveViewsList(uriListMaker.make(eventList))),
                HttpStatus.OK);
    }
}
