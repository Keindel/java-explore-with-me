package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;


    @Override
    public Category addCategory(NewCategoryDto newCategoryDto) {
        return null;
    }

    @Override
    public HttpStatus deleteCategory(Long catId) {
        return null;
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public User registerUser(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public HttpStatus deleteUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public List<Event> getEventsDetailed(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return null;
    }

    @Override
    public Event updateEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest) {
        return null;
    }

    @Override
    public Event publishEvent(Long eventId) {
        return null;
    }

    @Override
    public Event rejectEvent(Long eventId) {
        return null;
    }

    @Override
    public Compilation saveCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public HttpStatus deleteCompilation(Long compId) {
        return null;
    }

    @Override
    public HttpStatus removeEventFromCompilation(Long compId, Long eventId) {
        return null;
    }

    @Override
    public HttpStatus addEventToCompilation(Long compId, Long eventId) {
        return null;
    }

    @Override
    public HttpStatus unpinCompilation(Long compId) {
        return null;
    }

    @Override
    public HttpStatus pinCompilation(Long compId) {
        return null;
    }
}
