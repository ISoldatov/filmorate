package ru.yandex.practicum.filmorate.exception;

public class FilmValidationException extends ValidationException {
    public FilmValidationException(int id) {
        super("Ошибка данных о фильме id=", id);
    }

}
