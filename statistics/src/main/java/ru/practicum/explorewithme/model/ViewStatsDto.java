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
public class ViewStatsDto {

  private String app;

  private String uri;

  private Long hits;

  public ViewStatsDto(String app, String uri) {
    this.app = app;
    this.uri = uri;
  }
}
