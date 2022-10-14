package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.LocationAreaNotFoundException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.location.LocationArea;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {
    Category addCategory(NewCategoryDto newCategoryDto);

    void deleteCategory(Long catId);

    Category updateCategory(CategoryDto categoryDto);

    User registerUser(NewUserRequest newUserRequest);

    void deleteUser(Long userId);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    List<Event> getEventsDetailed(List<Long> users,
                                  List<State> states,
                                  List<Long> categories,
                                  LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                  Integer from, Integer size) throws EventTimeException;

    Event updateEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest) throws EventNotFoundException, RequestLogicException;

    Event publishEvent(Long eventId) throws EventNotFoundException, ForbiddenException, EventTimeException;

    Event rejectEvent(Long eventId) throws ForbiddenException, EventNotFoundException;

    Compilation saveCompilation(NewCompilationDto newCompilationDto) throws EventNotFoundException;

    void deleteCompilation(Long compId);

    void removeEventFromCompilation(Long compId, Long eventId) throws EventNotFoundException, CompilationNotFoundException;

    void addEventToCompilation(Long compId, Long eventId) throws EventNotFoundException, CompilationNotFoundException;

    void unpinCompilation(Long compId) throws CompilationNotFoundException;

    void pinCompilation(Long compId) throws CompilationNotFoundException;

    LocationArea addArea(LocationArea locationArea);

    List<LocationArea> getAreas();

    List<Event> getEventsInArea(Long areaId) throws LocationAreaNotFoundException;
}
