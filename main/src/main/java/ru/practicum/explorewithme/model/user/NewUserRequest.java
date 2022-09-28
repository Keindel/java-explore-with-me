package ru.practicum.explorewithme.model.user;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Данные нового пользователя
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NewUserRequest   {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String name;
}
