package ru.practicum.explorewithme.model.compilation;

import lombok.*;
import org.hibernate.Hibernate;
import ru.practicum.explorewithme.model.event.Event;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "compilations")
public class Compilation {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "compile_events_coupling",
            joinColumns = {@JoinColumn(name = "compilation_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    @ToString.Exclude
    private List<Event> events;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean pinned;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Compilation that = (Compilation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + Objects.hashCode(id);
    }
}
