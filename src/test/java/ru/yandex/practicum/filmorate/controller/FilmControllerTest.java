package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    void getAll() {
    }

    @Test
    void createNormalFilm() {
        Film film = new Film(1, "Фильм", "Описание", LocalDate.of(2022, 1, 1), 60);
        Film testFilm = filmController.create(film);
        Assertions.assertEquals(film, testFilm);

    }

    @Test
    void update() {
    }
}