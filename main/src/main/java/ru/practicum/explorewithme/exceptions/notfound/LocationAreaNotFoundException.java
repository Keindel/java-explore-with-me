package ru.practicum.explorewithme.exceptions.notfound;

public class LocationAreaNotFoundException extends Exception {

    public LocationAreaNotFoundException(String message) {
        super(message);
    }

    public LocationAreaNotFoundException(Long locId) {
        super((String.format("LocationArea with id=%d was not found.", locId)));
    }
}
