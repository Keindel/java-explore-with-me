package ru.practicum.explorewithme.model.compilation;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.explorewithme.model.event.EventShortDto;

import javax.validation.Valid;
import javax.validation.constraints.*;

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
