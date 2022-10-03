package ru.practicum.explorewithme.exceptions.notfound;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super(String.format("User with id=%d was not found.", userId));
    }
}
