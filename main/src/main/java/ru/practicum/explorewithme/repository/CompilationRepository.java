package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.compilation.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    Page<Compilation> findAllByPinned(Boolean pinned, Pageable page);
}
