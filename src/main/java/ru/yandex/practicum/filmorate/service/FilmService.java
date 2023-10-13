package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationUtil;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    @Qualifier("filmDbStorage")
    private FilmStorage storage;

    public Film create(Film film) {
        return storage.save(film);
    }

    public Film update(Film film) {
        return ValidationUtil.checkNotFoundWithId(storage.update(film), film.getId());
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

    public void setLike(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(storage.get(id), id);
        ValidationUtil.checkNotFoundWithId(storage.get(userId), userId);
        storage.get(id).getLikes().add(userId);
    }

    public void deleteLike(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(storage.get(id), id);
        ValidationUtil.checkNotFoundWithId(storage.get(userId), userId);
        storage.get(id).getLikes().remove(userId);
    }
}
