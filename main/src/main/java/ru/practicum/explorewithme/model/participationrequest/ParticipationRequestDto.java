package ru.practicum.explorewithme.model.participationrequest;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * Заявка на участие в событии
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipationRequestDto   {

  private String created;

  private Long event;

  @EqualsAndHashCode.Include
  private Long id;

  private Long requester;

  private String status;
}
