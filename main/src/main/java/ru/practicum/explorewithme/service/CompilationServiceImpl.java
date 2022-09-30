package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.compilation.Compilation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    @Override
    public Compilation getById(Long compId) {
        return null;
    }

    @Override
    public List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size) {
        return null;
    }
}
