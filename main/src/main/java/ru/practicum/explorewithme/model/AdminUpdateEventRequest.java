package ru.practicum.explorewithme.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.location.Location;

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

  private String eventDate;

  private Location location;

  private Boolean paid;

  private Integer participantLimit;

  private Boolean requestModeration;

  private String title;
}
