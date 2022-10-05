package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.model.compilation.Compilation;

import java.util.List;

public interface CompilationService {
    
    Compilation getById(Long compId) throws CompilationNotFoundException;

    List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size);
}
