package ru.yandex.practicum.filmorate.exception;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.model.AbstractBaseEntity;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@UtilityClass
public class ValidationUtil {

    public static final int MAX_DESCRIPTION_LENGTH = 200;
    public static final LocalDate EARLY_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    public static final int MIN_DURATION = 1;

    public void checkFilm(Film film) {
        if (film.getName().isBlank()) {
            throw new FilmValidationException("Название не может быть пустым.");
        }
        if (film.getDuration() < MIN_DURATION) {
            throw new FilmValidationException("Продолжительность фильма должна быть положительной.");
        }
        if (film.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            throw new FilmValidationException("Максимальная длина описания — 200 символов.");
        }
        if (film.getReleaseDate().isBefore(EARLY_RELEASE_DATE)) {
            throw new FilmValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " должно быть новое (id=null)");
        }
    }

    public static void checkNotNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " должно быть не новое (id=not null)");
        }
    }

}
