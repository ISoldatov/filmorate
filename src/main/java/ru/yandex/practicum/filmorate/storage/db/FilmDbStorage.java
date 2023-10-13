package ru.yandex.practicum.filmorate.storage.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component("filmDbStorage")
public class FilmDbStorage implements FilmStorage {
    private final Logger log = LoggerFactory.getLogger(UserDbStorage.class);
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film save(Film film) {
        String sqlQuery = "INSERT INTO films (name, description, release_date, duration, mpa)" +
                "VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);
        film.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return film;
    }

    @Override
    public Film update(Film film) {
        String sqlQuery = "UPDATE films SET " +
                "name = ?, description = ?, release_date = ?, duration = ?, mpa= ?" +
                "where id = ?";
        int numberRowAffect = jdbcTemplate.update(sqlQuery,
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa().getId(),
                film.getId());
        return numberRowAffect > 0 ? film : null;
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

        String sqlQuery = "SELECT * from films";
        List<Film> list = jdbcTemplate.query(sqlQuery, (rs, rowNum) -> makeFilm(rs));
        return list;
    }

    private Film makeFilm(ResultSet rs) throws SQLException {
//        LocalDate d = rs.getObject("birthdate", LocalDate.class);
        Film film = new Film(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getObject("release_date", LocalDate.class),
                rs.getInt("duration"),
                new MPA(rs.getInt("MPA"), rs.getString("TITLE")),
                getFilmGenre(rs.getInt("id"))
        );
        return film;
    }

    private void insertGenre(Film film) throws SQLException {

    }

    private Set<Genre> getFilmGenre(int filmId) {
        String sqlQuery = "SELECT g.ID , g.NAME FROM FILM_GENRE fg " +
                "INNER JOIN GENRES g ON g.ID =fg.ID_GENRE" +
                "WHERE fg.ID_FILM =? ";
        return new HashSet<Genre>(jdbcTemplate.query(sqlQuery, (rs, rowNum) -> makeGenre(rs), filmId));
    }

    private Genre makeGenre(ResultSet rs) throws SQLException {
        return new Genre(
                rs.getInt("id"),
                rs.getString("name"));
    }
}

