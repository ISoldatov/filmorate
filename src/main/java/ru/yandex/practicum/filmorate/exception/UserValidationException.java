package ru.yandex.practicum.filmorate.exception;

public class UserValidationException extends ValidationException {
    public UserValidationException() {
        super("Ошибка данных о пользователе");
    }

}
