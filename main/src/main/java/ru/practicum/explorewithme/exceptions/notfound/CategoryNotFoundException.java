package ru.practicum.explorewithme.exceptions.notfound;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long catId) {
        super((String.format("Category with id=%d was not found.", catId)));
    }
}
