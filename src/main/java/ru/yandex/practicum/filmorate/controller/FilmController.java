package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public List<Film> getAll() {
        log.debug("Запрос списка всех фильмов");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/film")
    public Film create(@RequestBody Film film) {
        log.debug("Добавлен фильм \"{}\"", film.getName());
        return films.putIfAbsent(film.getId(), film);
    }

    @PutMapping("/film")
    public Film update(@RequestBody Film film) {
        int idFilm = film.getId();
        log.debug("Обновлен фильм с id={}", idFilm);
        return films.computeIfPresent(idFilm, (i, f) -> film);
    }

}
