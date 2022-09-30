package ru.practicum.explorewithme.model.event;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Данные для изменения информации о событии
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateEventRequest   {

  private String annotation;

  private Long category;

  private String description;

  private String eventDate;

  private Long eventId;

  private Boolean paid;

  private Integer participantLimit;

  private String title;
}
