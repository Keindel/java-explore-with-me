package ru.practicum.explorewithme.model.participationrequest;

import lombok.*;
import org.hibernate.Hibernate;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "participation_requests")
public class ParticipationRequest {

    private LocalDateTime created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    private Event event;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User requester;

    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipationRequest that = (ParticipationRequest) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + Objects.hashCode(id);
    }
}
