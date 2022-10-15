package ru.practicum.explorewithme.model.event;

public enum State {
    PENDING("PENDING"),

    PUBLISHED("PUBLISHED"),

    CANCELED("CANCELED");

    private String value;

    State(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
