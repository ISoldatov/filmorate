package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @DeleteMapping("/films/{id}")
    public void delete(@PathVariable int id) {
        log.debug("Удаление фильма с id={}", id);
        service.delete(id);
    }

    @GetMapping("/films/{id}")
    public Film get(@PathVariable int id) {
        log.debug("Получение фильм с id={}", id);
        return service.get(id);
    }

    @GetMapping("/films")
    public List<Film> getAll() {
        log.debug("Запрос списка всех фильмов");
        return service.getAll();
    }

    @PutMapping("/films//{id}/like/{userId}")
    public void setLike(@PathVariable int id, @PathVariable int userId) {
        log.debug("Пользователь id={} ставит like фильму c id={}", id, userId);
        service.setLike(id, userId);
    }

    @DeleteMapping("/films//{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        log.debug("Пользователь id={} удаляет like фильму c id={}", id, userId);
        service.deleteLike(id, userId);
    }

    @GetMapping(value = {"/films/popular", "/films/popular?count={count}"})
    public List<Film> popularFilms(@PathVariable(required = false) String count) {
        int number = count == null ? 10 : Integer.parseInt(count);
        return service.getAll().stream()
                .sorted(Comparator.comparing(Film::getCountLikes).reversed())
                .limit(number)
                .collect(Collectors.toList());
    }

}
