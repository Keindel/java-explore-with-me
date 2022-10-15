package ru.practicum.explorewithme.model.event;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Данные для изменения информации о событии
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateEventRequest   {

  private String annotation;

  private Long category;

  private String description;

  private LocalDateTime eventDate;

  @NotNull
  @Min(1)
  private Long eventId;

  private Boolean paid;

  @Min(0)
  private Integer participantLimit;

  private String title;
}
