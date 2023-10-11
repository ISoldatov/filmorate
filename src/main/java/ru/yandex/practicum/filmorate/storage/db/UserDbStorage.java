package ru.yandex.practicum.filmorate.storage.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component("userDbStorage")
public class UserDbStorage implements UserStorage {
    private final Logger log = LoggerFactory.getLogger(UserDbStorage.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sqlQuery = "insert into users(email, login, name, birthday) " +
                "values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getBirthday().toString());
            return stmt;
        }, keyHolder);
        user.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return user;
    }

    @Override
    public User update(User user) {
        String sqlQuery = "update users set " +
                "email = ?, login = ?, name = ?, birthday = ?" +
                "where id = ?";
        int numberRowAffect = jdbcTemplate.update(sqlQuery,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday().toString(),
                user.getId());

        return numberRowAffect > 0 ? user : null;
    }

    @Override
    public boolean delete(int id) {
        String sqlQuery = "delete from users where id = ?";
        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    @Override
    public User get(int id) {
        String sqlQuery = "SELECT * FROM users where id = ?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sqlQuery, id);

        if (userRows.next()) {
            User user = new User(
                    userRows.getInt("id"),
                    userRows.getString("email"),
                    userRows.getString("login"),
                    userRows.getString("name"),
                    userRows.getObject("birthdate", LocalDate.class)
            );

            log.info("Найден пользователь: {} {}", user.getId(), user.getLogin());

            return user;
        } else {
            log.info("Пользователь с идентификатором {} не найден.", id);
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        String sqlQuery = "SELECT * from users";
        List<User> list = jdbcTemplate.query(sqlQuery, (rs, rowNum) -> makeUser(rs));
        return list;
    }

    private User makeUser(ResultSet rs) throws SQLException {
//        LocalDate d = rs.getObject("birthdate", LocalDate.class);
        User user = new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("login"),
                rs.getString("name"),
                rs.getObject("birthday", LocalDate.class)
        );
        return user;
    }

}
