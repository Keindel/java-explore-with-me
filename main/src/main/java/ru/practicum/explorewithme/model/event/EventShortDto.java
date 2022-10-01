package ru.practicum.explorewithme.model.event;

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

  private String annotation = null;

  private CategoryDto category = null;

  private Long confirmedRequests = null;

  private String eventDate = null;

  @EqualsAndHashCode.Include
  private Long id = null;

  private UserShortDto initiator = null;

  private Boolean paid = null;

  private String title = null;

  private Long views = null;
}
