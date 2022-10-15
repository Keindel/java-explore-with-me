package ru.practicum.explorewithme.model.compilation;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;

/**
 * Подборка событий
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompilationDto   {

  private List<EventShortDto> events;

  @EqualsAndHashCode.Include
  private Long id;

  private Boolean pinned;

  private String title;
}
