package ru.practicum.explorewithme.model.category;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Категория
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDto   {

  @NotNull
  @Min(1)
  @EqualsAndHashCode.Include
  private Long id;

  @NotBlank
  private String name;
}
