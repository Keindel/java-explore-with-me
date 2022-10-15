package ru.practicum.explorewithme.model.location;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
@Table(name = "location_areas")
public class LocationArea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private BigDecimal lat;

  @NotNull
  private BigDecimal lon;

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @Min(1)
  private Integer radius;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    LocationArea location = (LocationArea) o;
    return id != null && Objects.equals(id, location.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
