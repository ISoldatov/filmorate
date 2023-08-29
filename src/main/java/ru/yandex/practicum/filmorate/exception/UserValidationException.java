package ru.yandex.practicum.filmorate.exception;

public class UserValidationException extends ValidationException {
    public UserValidationException(int id) {
        super("Ошибка данных о пользователе id=", id);
    }

}
