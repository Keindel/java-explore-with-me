/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.practicum.explorewithme.api.foradmin;

import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface AdminApi {

    @RequestMapping(value = "/admin/categories",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody NewCategoryDto body);

    @RequestMapping(value = "/admin/categories",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto body);

    @RequestMapping(value = "/admin/categories/{catId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCategory(@PathVariable("catId") Long catId);

    @RequestMapping(value = "/admin/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<UserDto> registerUser(@Valid @RequestBody NewUserRequest body);

    @RequestMapping(value = "/admin/users/{userId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/admin/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<UserDto>> getUsers(@Valid @RequestParam(value = "ids", required = false) List<Long> ids,
                                           @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                           @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size);

    @RequestMapping(value = "/admin/events",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<EventFullDto>> getEventsDetailed(@Valid @RequestParam(value = "users", required = false) List<Long> users,
                                                         @Valid @RequestParam(value = "states", required = false) List<State> states,
                                                         @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
                                                         @Valid @RequestParam(value = "rangeStart", required = false) LocalDateTime rangeStart,
                                                         @Valid @RequestParam(value = "rangeEnd", required = false) LocalDateTime rangeEnd,
                                                         @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                         @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws EventTimeException;

    @RequestMapping(value = "/admin/events/{eventId}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<EventFullDto> updateEvent(@PathVariable("eventId") Long eventId,
                                             @Valid @RequestBody AdminUpdateEventRequest body) throws RequestLogicException, EventNotFoundException;

    @RequestMapping(value = "/admin/events/{eventId}/publish",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<EventFullDto> publishEvent(@PathVariable("eventId") Long eventId) throws EventTimeException, ForbiddenException, EventNotFoundException;

    @RequestMapping(value = "/admin/events/{eventId}/reject",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<EventFullDto> rejectEvent(@PathVariable("eventId") Long eventId) throws ForbiddenException, EventNotFoundException;

    @RequestMapping(value = "/admin/compilations",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CompilationDto> saveCompilation(@Valid @RequestBody NewCompilationDto body) throws EventNotFoundException;

    @RequestMapping(value = "/admin/compilations/{compId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCompilation(@PathVariable("compId") Long compId);

    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> removeEventFromCompilation(@PathVariable("eventId") Long eventId,
                                                    @PathVariable Long compId) throws CompilationNotFoundException, EventNotFoundException;

    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<Void> addEventToCompilation(@PathVariable("compId") Long compId,
                                               @PathVariable("eventId") Long eventId) throws CompilationNotFoundException, EventNotFoundException;

    @RequestMapping(value = "/admin/compilations/{compId}/pin",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> unpinCompilation(@PathVariable("compId") Long compId) throws CompilationNotFoundException;

    @RequestMapping(value = "/admin/compilations/{compId}/pin",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<Void> pinCompilation(@PathVariable("compId") Long compId) throws CompilationNotFoundException;
}

