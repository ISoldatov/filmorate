package ru.yandex.practicum.filmorate.exception;

public abstract class ValidationException extends RuntimeException {

    private int id;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, int id) {
        super(message);
        this.id = id;
    }

}
