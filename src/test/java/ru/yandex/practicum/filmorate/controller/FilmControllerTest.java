package ru.yandex.practicum.filmorate.controller;

//import static ru.yandex.practicum.filmorate.FilmTestData.filmNotId_1;
//import static ru.yandex.practicum.filmorate.FilmTestData.filmNotId_2;

class FilmControllerTest {
/*
    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    void getAll() {
        Film[] expectedFilms = new Film[]{filmNotId_1, filmNotId_2};
        filmController.create(filmNotId_1);
        filmController.create(filmNotId_2);
        Assertions.assertArrayEquals(expectedFilms, filmController.getAll().toArray());
    }

    @Test
    void createNormal() {
        Film film = new Film("Фильм", "Описание", LocalDate.of(2022, 1, 1), 60);
        Film testFilm = filmController.create(film);
        Assertions.assertEquals(film, testFilm, "Переданный и сохраненный фильм не совпадают");
    }

    @Test
    void createNotValidFields() {
        Film filmName = new Film("  ", "Описание", LocalDate.of(2022, 1, 1), 60);
        Assertions.assertThrows(FilmValidationException.class, () -> filmController.create(filmName),
                "Д/б FilmValidationException т.к. наименование фильмы - пустая строка");

        Film filmDur = new Film("Фильм", "Описание", LocalDate.of(2022, 1, 1), -1);
        Assertions.assertThrows(FilmValidationException.class, () -> filmController.create(filmDur),
                "Д/б FilmValidationException т.к продолжительность фильма меньше " + FilmController.MIN_DURATION);

        Film filmDate = new Film("Фильм", "Описание", LocalDate.of(1895, 12, 27), 60);
        Assertions.assertThrows(FilmValidationException.class, () -> filmController.create(filmDate),
                "Д/б FilmValidationException т.к дата выхода фильма ранее " + FilmController.EARLY_RELEASE_DATE);

        String description = new String(new char[201]);
        Film filmDesc = new Film("Фильм", description, LocalDate.of(1895, 12, 29), 60);
        Assertions.assertThrows(FilmValidationException.class, () -> filmController.create(filmDesc),
                "Д/б FilmValidationException т.к описание фильма более " + FilmController.MAX_DESCRIPTION_LENGTH + " символов");
    }

    @Test
    void createBoundaryFields() {
        Film filmName = new Film("", "Описание", LocalDate.of(2022, 1, 1), 60);
        Assertions.assertThrows(FilmValidationException.class, () -> filmController.create(filmName),
                "Д/б FilmValidationException т.к. наименование фильмы - пустая строка");

        Film filmDur = new Film("Фильм", "Описание", LocalDate.of(2022, 1, 1), 1);
        Assertions.assertDoesNotThrow(() -> filmController.create(filmDur),
                "Продолжительность фильма равна " + FilmController.MIN_DURATION + " - исключения не должно быть");

        Film filmDate = new Film("Фильм", "Описание", LocalDate.of(1895, 12, 28), 60);
        Assertions.assertDoesNotThrow(() -> filmController.create(filmDate),
                "Дата выхода фильма равна " + FilmController.EARLY_RELEASE_DATE + " - исключения не должно быть");

        String description = new String(new char[200]);
        Film filmDesc = new Film("Фильм", description, LocalDate.of(1895, 12, 29), 60);
        Assertions.assertDoesNotThrow(() -> filmController.create(filmDesc),
                "Описание фильма равно " + FilmController.MAX_DESCRIPTION_LENGTH + " символов - исключения не должно быть");
    }

    @Test
    void updateNormal() {
        Film film = new Film("Фильм", "Описание", LocalDate.of(2022, 1, 1), 60);
        filmController.create(film);
        Film filmUp = new Film(1, "ФильмОбн", "ОписаниеОбн", LocalDate.of(2022, 2, 2), 30);
        Film testFilm = filmController.update(filmUp);
        Assertions.assertEquals(filmUp, testFilm, "Сохраненный и обновленный фильм не совпадают");
    }
    */

}