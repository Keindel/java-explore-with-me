package ru.practicum.explorewithme.model.category;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
