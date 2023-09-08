package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public class InMemoryFilmStorage implements FilmStorage {

    @Override
    public Film save(Film film) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Film get(int id) {
        return null;
    }

    @Override
    public List<Film> getAll() {
        return null;
    }
}
