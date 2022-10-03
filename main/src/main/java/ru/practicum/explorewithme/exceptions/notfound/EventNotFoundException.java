package ru.practicum.explorewithme.exceptions.notfound;

public class EventNotFoundException extends Exception {

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Long eventId) {
        super((String.format("Event with id=%d was not found.", eventId)));
    }
}
