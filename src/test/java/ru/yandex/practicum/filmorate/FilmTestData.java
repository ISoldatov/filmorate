package ru.yandex.practicum.filmorate;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.GenreType;
import ru.yandex.practicum.filmorate.model.MPAType;

import java.time.LocalDate;
import java.util.Set;

@UtilityClass
public class FilmTestData {
//    public static final Film filmNotId_1 = new Film("name_1", "description_1", LocalDate.of(2020, 1, 1), 30);
//    public static final Film filmNotId_2 = new Film("name_2", "description_2", LocalDate.of(2020, 2, 2), 60);

    public static final Film filmHasId_1 = new Film(1, "name_1", "description_1", LocalDate.of(2020, 1, 1), 30, Set.of(GenreType.ACTION, GenreType.COMEDY), MPAType.R);
    public static final Film filmHasId_2 = new Film(2, "name_2", "description_2", LocalDate.of(2020, 2, 2), 60, Set.of(GenreType.CARTOON), MPAType.PG13);

}
