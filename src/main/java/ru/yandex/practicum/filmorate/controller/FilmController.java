package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FilmController {

    public static final int MAX_DESCRIPTION_LENGTH = 200;
    public static final LocalDate EARLY_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    public static final int MIN_DURATION = 0;

    private final Map<Integer, Film> films = new HashMap<>();


    @GetMapping("/films")
    public List<Film> getAll() {
        log.debug("Запрос списка всех фильмов");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/film")
    public Film create(@RequestBody Film film) {
        log.debug("Добавлен фильм \"{}\"", film.getName());
        checkFilm(film);
        return films.computeIfAbsent(film.getId(), v -> film);
    }

    @PutMapping("/film")
    public Film update(@RequestBody Film film) {
        log.debug("Обновлен фильм с id={}", film.getId());
        checkFilm(film);
        return films.computeIfPresent(film.getId(), (i, f) -> film);
    }

    private void checkFilm(Film film) {
        int idFilm = film.getId();
        if (film.getName().isBlank() || film.getDuration() < MIN_DURATION ||
                film.getDescription().length() > MAX_DESCRIPTION_LENGTH ||
                film.getReleaseDate().isBefore(EARLY_RELEASE_DATE)) {
            throw new FilmValidationException(idFilm);
        }
    }

}
