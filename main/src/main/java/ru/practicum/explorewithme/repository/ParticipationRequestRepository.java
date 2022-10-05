package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.model.user.User;

import java.util.List;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {

    List<ParticipationRequest> findAllByEvent(Event event);

    List<ParticipationRequest> findAllByStatusAndEvent(Status status, Event event);

    List<ParticipationRequest> findAllByRequester(User user);
}
