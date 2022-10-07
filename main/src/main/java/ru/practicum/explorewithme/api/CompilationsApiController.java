package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.notfound.CompilationNotFoundException;
import ru.practicum.explorewithme.mapper.CompilationMapper;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.CompilationService;
import ru.practicum.explorewithme.mapper.ListModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationsApiController implements CompilationsApi {

    private final CompilationMapper compilationMapper;

    private final CompilationService compilationService;

    @GetMapping("/{compId}")
    public ResponseEntity<CompilationDto> getCompilationById(@PathVariable("compId") Long compId) throws CompilationNotFoundException {
        return new ResponseEntity<>(compilationMapper.mapToDto(compilationService.getById(compId)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompilationDto>> getCompilations(@Valid @RequestParam(value = "pinned", required = false) Boolean pinned,
                                                                @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                                @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(compilationService
                .getCompilations(pinned, from, size)
                .stream().map(compilationMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
