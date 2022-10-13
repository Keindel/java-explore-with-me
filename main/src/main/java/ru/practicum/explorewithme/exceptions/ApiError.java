package ru.practicum.explorewithme.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * Сведения об ошибке
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ApiError {

    private StackTraceElement[] errors;

    private String message;

    private String reason;

    private HttpStatus status;

    private LocalDateTime timestamp;
}
