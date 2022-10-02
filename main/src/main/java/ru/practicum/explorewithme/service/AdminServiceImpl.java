package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.model.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.CompilationRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.UserRepository;

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
        return null;
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public User registerUser(NewUserRequest newUserRequest) {
        return userRepository.save(modelMapper.map(newUserRequest, User.class));
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
    public Compilation saveCompilation(NewCompilationDto newCompilationDto) throws EventNotFoundException {
        List<Long> eventIds = newCompilationDto.getEvents();
        List<Event> events = new ArrayList<>();
        for (Long eventId : eventIds) {
            events.add(eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId)));
        }
        Compilation compilation = new Compilation(events, null, newCompilationDto.getPinned(), newCompilationDto.getTitle());

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
