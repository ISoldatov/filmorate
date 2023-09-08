package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class FilmController {

    public static final int MAX_DESCRIPTION_LENGTH = 200;
    public static final LocalDate EARLY_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    public static final int MIN_DURATION = 0;
    private final Map<Integer, Film> films = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/films")
    public List<Film> getAll() {
        log.debug("Запрос списка всех фильмов");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) {
        log.debug("Добавлен фильм \"{}\"", film.getName());
        checkFilm(film);
        if (film.getId() == null) {
            film.setId(counter.incrementAndGet());
        }
        return films.computeIfAbsent(film.getId(), v -> film);
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        int idFilm = film.getId();
        log.debug("Обновлен фильм с id={}", idFilm);
        checkFilm(film);
        if (!films.containsKey(idFilm)) {
            throw new FilmValidationException();
        }
        return films.computeIfPresent(idFilm, (i, f) -> film);
    }

    private void checkFilm(Film film) {
        if (film.getName().isBlank() || film.getDuration() < MIN_DURATION ||
                film.getDescription().length() > MAX_DESCRIPTION_LENGTH ||
                film.getReleaseDate().isBefore(EARLY_RELEASE_DATE)) {
            throw new FilmValidationException();
        }
    }
}
