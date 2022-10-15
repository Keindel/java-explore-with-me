package ru.practicum.explorewithme.model.event;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.location.Location;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * Информация для редактирования события администратором. Все поля необязательные. Значение полей не валидируется.
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AdminUpdateEventRequest   {

  private String annotation;

  private Long category;

  private String description;

  private LocalDateTime eventDate;

  private Location location;

  private Boolean paid;

  @Min(0)
  private Integer participantLimit;

  private Boolean requestModeration;

  private String title;
}
