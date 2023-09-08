package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> storage = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Film save(Film film) {
        if (film.isNew()) {
            film.setId(counter.incrementAndGet());
            storage.put(film.getId(), film);
            return film;
        }
        return storage.computeIfPresent(film.getId(), (id, oldFilm) -> film);
    }

    @Override
    public boolean delete(int id) {
        return storage.remove(id) != null;
    }

    @Override
    public Film get(int id) {
        return storage.get(id);
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(storage.values());
    }
}
