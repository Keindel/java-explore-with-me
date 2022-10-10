package ru.practicum.explorewithme.model.compilation;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Подборка событий
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NewCompilationDto {

    @NotNull
    private List<Long> events;

    @NotNull
    private Boolean pinned;

    @NotBlank
    private String title;
}
