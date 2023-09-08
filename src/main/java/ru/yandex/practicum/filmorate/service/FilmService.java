package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

public class FilmService {

    private FilmStorage storage;

    public FilmService(FilmStorage storage) {
        this.storage = storage;
    }

    public Film create(Film film) {
        return storage.save(film);
    }

    public Film update(Film film) {
        return storage.save(film);
    }

    public Film get(int id) {
        return storage.get(id);
    }

    public List<Film> getAll() {
        return storage.getAll();
    }
}
