package ru.yandex.practicum.filmorate.exception;

public abstract class ValidationException extends RuntimeException {

    private final int id;

    public ValidationException(String message, int id) {
        super(message);
        this.id = id;
    }
}
