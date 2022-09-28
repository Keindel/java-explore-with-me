package ru.practicum.explorewithme.model.user;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Пользователь
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto   {

  @NotNull
  @Min(1)
  @EqualsAndHashCode.Include
  private Long id;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String name;
}
