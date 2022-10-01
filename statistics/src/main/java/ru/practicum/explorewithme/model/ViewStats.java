package ru.practicum.explorewithme.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * ViewStats
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ViewStats   {

  private String app;

  private String uri;

  private Long hits;
}
