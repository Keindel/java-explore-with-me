package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.exceptions.notfound.EventNotFoundException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class ParticipationMapper {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private Converter<Event, Long> convertEventToLong = src
            -> src.getSource() == null ? null : this.getEventId(src.getSource());

    private Converter<User, Long> convertUserToLong = src
            -> src.getSource() == null ? null : this.getUserId(src.getSource());

    public ParticipationRequestDto mapToParticipationRequestDto(ParticipationRequest req) {
        modelMapper.typeMap(ParticipationRequest.class, ParticipationRequestDto.class)
                .addMappings(m -> m.using(convertEventToLong)
                        .map(ParticipationRequest::getEvent, ParticipationRequestDto::setEvent))
                .addMappings(m -> m.using(convertUserToLong)
                        .map(ParticipationRequest::getRequester, ParticipationRequestDto::setRequester));
        return modelMapper.map(req, ParticipationRequestDto.class);
    }

    private Long getEventId(Event event) {
        return event.getId();
    }

    private Long getUserId(User user) {
        return user.getId();
    }
}
