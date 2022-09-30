package ru.practicum.explorewithme.model.event;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.user.UserShortDto;

/**
 * EventFullDto
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventFullDto   {

  private String annotation;

  private CategoryDto category;

  private Long confirmedRequests;

  private String createdOn;

  private String description;

  private String eventDate;

  @EqualsAndHashCode.Include
  private Long id;

  private UserShortDto initiator;

  private Location location;

  private Boolean paid;

  private Integer participantLimit;

  private String publishedOn;

  private Boolean requestModeration;

  private State state;

  private String title;

  private Long views;
}
