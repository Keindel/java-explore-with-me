package ru.practicum.explorewithme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;


@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EndpointHitDto {

  private String app;

  private String uri;

  private String ip;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;
}
