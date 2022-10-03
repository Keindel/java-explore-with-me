package ru.practicum.explorewithme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.exceptions.notfound.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@RestControllerAdvice(basePackages = {"ru.practicum.explorewithme.service"})
public class ErrorHandler {

    private static final String CONDITIONS_NOT_MET = "For the requested operation the conditions are not met.";
    private static final String NOT_FOUND = "The required object was not found.";


    @ExceptionHandler({UserNotFoundException.class,
            CategoryNotFoundException.class,
            CompilationNotFoundException.class,
            EventNotFoundException.class,
            LocationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                NOT_FOUND,
                ApiError.StatusEnum._404_NOT_FOUND,
                LocalDateTime.now());
    }
//
//    @ExceptionHandler({BookingValidationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleBadRequest(final Exception e) {
//        return Map.of("error: ", "check your request");
//    }
//
//    @ExceptionHandler({UnsupportedStateException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleUnsupported(final Exception e) {
//        return new ErrorResponse(e.getMessage());
//    }

    @ExceptionHandler({EventTimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadEvent(final Exception e) {
        return new ApiError(e.getStackTrace(),
                e.getMessage(),
                CONDITIONS_NOT_MET,
                ApiError.StatusEnum._403_FORBIDDEN,
                LocalDateTime.now());
    }
}