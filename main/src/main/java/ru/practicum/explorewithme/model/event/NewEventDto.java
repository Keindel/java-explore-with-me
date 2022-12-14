package ru.practicum.explorewithme.model.event;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.location.Location;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Новое событие
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NewEventDto   {

  @NotBlank
  private String annotation;

  @NotNull
  @Min(1)
  private Long category;

  @NotBlank
  private String description;

  @Future
  private LocalDateTime eventDate;

  @NotNull
  private Location location;

  private Boolean paid;

  @Min(0)
  private Integer participantLimit;

  private Boolean requestModeration;

  @NotBlank
  private String title;
}
