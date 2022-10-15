package ru.practicum.explorewithme.model.user;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Пользователь (краткая информация)
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserShortDto   {

  @NotNull
  @Min(1)
  @EqualsAndHashCode.Include
  private Long id;

  @NotBlank
  private String name;
}
