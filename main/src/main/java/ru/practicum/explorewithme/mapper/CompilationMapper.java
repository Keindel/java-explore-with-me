package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    public CompilationDto mapToDto(Compilation compilation) {
        List<EventShortDto> eventShortDtoList = compilation.getEvents()
                .stream().map(eventMapper::mapToShortDto).collect(Collectors.toList());
        CompilationDto compilationDto = modelMapper.map(compilation, CompilationDto.class);
        compilationDto.setEvents(eventShortDtoList);
        return compilationDto;
    }
}
