package ru.practicum.explorewithme.model.compilation;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.validation.annotation.Validated;
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
@EqualsAndHashCode
public class NewCompilationDto   {

  @NotNull
  private List<Long> events ;

  @NotNull
  private Boolean pinned;

  @NotBlank
  private String title;
}
