package ru.practicum.explorewithme.model.event;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.user.UserShortDto;

import java.time.LocalDateTime;

/**
 * Краткая информация о событии
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventShortDto   {

  private String annotation;

  private CategoryDto category;

  private Long confirmedRequests;

  private LocalDateTime eventDate;

  @EqualsAndHashCode.Include
  private Long id;

  private UserShortDto initiator;

  private Boolean paid;

  private String title;

  private Long views;
}
