package ru.practicum.explorewithme.model.category;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Данные для добавления новой категории
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewCategoryDto   {

  @NotBlank
  private String name;
}
