package ru.practicum.explorewithme.model.location;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Широта и долгота места проведения события
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "locations")
public class Location   {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private BigDecimal lat;

  @NotNull
  private BigDecimal lon;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Location location = (Location) o;
    return id != null && Objects.equals(id, location.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
