package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.CompilationRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.UserRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final CompilationRepository compilationRepository;

    private final EventRepository eventRepository;

    @Override
    public Category addCategory(NewCategoryDto newCategoryDto) {
        return categoryRepository.save(modelMapper.map(newCategoryDto, Category.class));
    }

    @Override
    public HttpStatus deleteCategory(Long catId) {
        categoryRepository.deleteById(catId);
        return HttpStatus.OK;
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto) {
        return categoryRepository.save(modelMapper.map(categoryDto, Category.class));
    }

    @Override
    public User registerUser(NewUserRequest newUserRequest) {
        return userRepository.save(modelMapper.map(newUserRequest, User.class));
    }

    @Override
    public HttpStatus deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    @Override
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        Pageable page = CustomPageable.of(from, size);
        if (ids != null) {
            return userRepository.findAllByIdList(ids, page).getContent();
        }
        return userRepository.findAll(page).getContent();
    }

    @Override
    public List<Event> getEventsDetailed(List<Long> users,
                                         List<State> states,
                                         List<Long> categories,
                                         LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                         Integer from, Integer size) {
        Pageable page = CustomPageable.of(from, size);
        rangeStart = (rangeStart == null) ? LocalDateTime.MIN : rangeStart;
        rangeEnd = (rangeEnd == null) ? LocalDateTime.MAX : rangeEnd;
        return eventRepository.findAllByParams(users, states, categories, rangeStart, rangeEnd, page).getContent();
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
    public Compilation saveCompilation(NewCompilationDto newCompilationDto) throws EventNotFoundException {
        List<Long> eventIds = newCompilationDto.getEvents();
        List<Event> events = new ArrayList<>();
        for (Long eventId : eventIds) {
            events.add(eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId)));
        }
        Compilation compilation = modelMapper.map(newCompilationDto, Compilation.class);
        compilation.setEvents(events);
        return compilationRepository.save(compilation);
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
