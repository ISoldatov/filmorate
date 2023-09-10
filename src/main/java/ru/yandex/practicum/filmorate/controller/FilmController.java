package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.filmorate.exception.ValidationUtil.*;

@Slf4j
@RestController
public class FilmController {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) {
        log.debug("Добавление фильма \"{}\"", film.getName());
        checkNew(film);
        checkFilm(film);
        return service.create(film);
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        log.debug("Обновление фильма \"{}\"", film.getName());
        checkNotNew(film);
        checkFilm(film);
        return service.update(film);
    }


    public Film get(int id) {
        log.debug("Получение фильм с id={}", id);
        return service.get(id);
    }

    @GetMapping("/films")
    public List<Film> getAll() {
        log.debug("Запрос списка всех фильмов");
        return new ArrayList<>();
    }

}
