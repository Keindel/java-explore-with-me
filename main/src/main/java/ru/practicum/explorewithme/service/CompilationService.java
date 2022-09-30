package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.model.compilation.Compilation;

import java.util.List;

public interface CompilationService {
    
    Compilation getById(Long compId);

    List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size);
}
