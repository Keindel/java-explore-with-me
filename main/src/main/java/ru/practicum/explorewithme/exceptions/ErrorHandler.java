package ru.practicum.explorewithme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.exceptions.notfound.*;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = {"ru.practicum.explorewithme.service"})
public class ErrorHandler {

    private static final String CONDITIONS_NOT_MET = "For the requested operation the conditions are not met.";
    private static final String NOT_FOUND = "The required object was not found.";


    @ExceptionHandler({CategoryNotFoundException.class,
            CompilationNotFoundException.class,
            EventNotFoundException.class,
            LocationNotFoundException.class,
            ParticipationRequestNotFoundException.class,
            UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                NOT_FOUND,
                HttpStatus.NOT_FOUND,
                LocalDateTime.now());
    }

    @ExceptionHandler({EventTimeException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleEventBadTime(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                CONDITIONS_NOT_MET,
                HttpStatus.CONFLICT,
                LocalDateTime.now());
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbidden(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                CONDITIONS_NOT_MET,
                HttpStatus.FORBIDDEN,
                LocalDateTime.now());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiError handleIllegalArgument(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                CONDITIONS_NOT_MET,
                HttpStatus.NOT_ACCEPTABLE,
                LocalDateTime.now());
    }
}