package ru.practicum.explorewithme.model.event;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.user.UserShortDto;

import javax.validation.Valid;
import javax.validation.constraints.*;

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
