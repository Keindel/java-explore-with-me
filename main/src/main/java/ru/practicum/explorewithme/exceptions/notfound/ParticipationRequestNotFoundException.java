package ru.practicum.explorewithme.exceptions.notfound;

public class ParticipationRequestNotFoundException extends Exception {

    public ParticipationRequestNotFoundException(String message) {
        super(message);
    }

    public ParticipationRequestNotFoundException(Long reqId) {
        super(String.format("ParticipationRequest with id=%d was not found.", reqId));
    }
}
