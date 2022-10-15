package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.repository.CompilationRepository;
import ru.practicum.explorewithme.util.CustomPageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;

    @Override
    public Compilation getById(Long compId) throws CompilationNotFoundException {
        return compilationRepository.findById(compId).orElseThrow(()
                -> new CompilationNotFoundException(compId));
    }

    @Override
    public List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size) {
        Pageable page = CustomPageable.of(from, size);
        if (pinned != null) {
            return compilationRepository.findAllByPinned(pinned, page).getContent();
        }
        return compilationRepository.findAll(page).getContent();
    }
}
