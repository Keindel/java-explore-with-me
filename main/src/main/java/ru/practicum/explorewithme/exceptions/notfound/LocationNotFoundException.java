package ru.practicum.explorewithme.exceptions.notfound;

public class LocationNotFoundException extends Exception {

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Long locId) {
        super((String.format("Location with id=%d was not found.", locId)));
    }
}
