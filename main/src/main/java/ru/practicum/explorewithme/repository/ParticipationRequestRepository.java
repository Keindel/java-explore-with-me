package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.Status;
import ru.practicum.explorewithme.model.user.User;

import java.util.List;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {

    List<ParticipationRequest> findAllByEvent(Event event);

    List<ParticipationRequest> findAllByStatusAndEvent(Status status, Event event);

    //TODO CHECK
    @Query("SELECT COUNT(req) FROM ParticipationRequest req " +
            "WHERE req.event = :event " +
            "AND req.status = :status ")
    Long countByStatusAndEvent(Status status, Event event);
//    @Query("SELECT COUNT(req) AS reqCount " +
//            "FROM ParticipationRequest req " +
//            "WHERE req.event = :event " +
//            "AND req.status = :status ")
//////    List<ReqCount> countByStatusAndEvent(Status status, Event event);
//    ReqCount countByStatusAndEvent(Status status, Event event);

    List<ParticipationRequest> findAllByRequester(User user);
}
