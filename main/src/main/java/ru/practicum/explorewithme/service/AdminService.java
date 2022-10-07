package ru.practicum.explorewithme.service;

import org.springframework.http.HttpStatus;
import ru.practicum.explorewithme.exceptions.EventTimeException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.RequestLogicException;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {
    Category addCategory(NewCategoryDto newCategoryDto);

    HttpStatus deleteCategory(Long catId);

    Category updateCategory(CategoryDto categoryDto);

    User registerUser(NewUserRequest newUserRequest);

    HttpStatus deleteUser(Long userId);

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

    HttpStatus deleteCompilation(Long compId);

    HttpStatus removeEventFromCompilation(Long compId, Long eventId) throws EventNotFoundException, CompilationNotFoundException;

    HttpStatus addEventToCompilation(Long compId, Long eventId) throws EventNotFoundException, CompilationNotFoundException;

    HttpStatus unpinCompilation(Long compId) throws CompilationNotFoundException;

    HttpStatus pinCompilation(Long compId) throws CompilationNotFoundException;
}
