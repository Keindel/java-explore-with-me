package ru.practicum.explorewithme.exceptions.notfound;

public class CompilationNotFoundException extends Exception {

    public CompilationNotFoundException(String message) {
        super(message);
    }

    public CompilationNotFoundException(Long compId) {
        super((String.format("Compilation with id=%d was not found.", compId)));
    }
}
