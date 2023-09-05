package ru.yandex.practicum.filmorate.exception;

public class FilmValidationException extends ValidationException {
    public FilmValidationException() {
        super("Ошибка в данных о фильме ");
    }

}
