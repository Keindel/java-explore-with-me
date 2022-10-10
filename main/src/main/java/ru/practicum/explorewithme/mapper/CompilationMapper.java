package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.statsclient.UriListMaker;
import ru.practicum.explorewithme.statsclient.ViewsStatsRetriever;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final ModelMapper modelMapper;

    private final EventMapper eventMapper;

    private final UriListMaker uriListMaker;

    private final ViewsStatsRetriever viewsStatsRetriever;

    public CompilationDto mapToDto(Compilation compilation) {
        List<Event> eventList = compilation.getEvents();
        List<EventShortDto> eventShortDtoList = eventMapper.mapToEventShortDtoList(eventList,
                viewsStatsRetriever.retrieveViewsList(uriListMaker.make(eventList)));
        CompilationDto compilationDto = modelMapper.map(compilation, CompilationDto.class);
        compilationDto.setEvents(eventShortDtoList);
        return compilationDto;
    }
}
