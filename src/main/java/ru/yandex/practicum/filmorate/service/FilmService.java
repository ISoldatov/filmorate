package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationUtil;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private final FilmStorage storage;

    public FilmService(FilmStorage storage) {
        this.storage = storage;
    }

    public Film create(Film film) {
        return storage.save(film);
    }

    public Film update(Film film) {
        return ValidationUtil.checkNotFoundWithId(storage.save(film), film.getId());
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(storage.delete(id), id);
    }

    public Film get(int id) {
        return ValidationUtil.checkNotFoundWithId(storage.get(id), id);
    }

    public List<Film> getAll() {
        return storage.getAll();
    }
}
