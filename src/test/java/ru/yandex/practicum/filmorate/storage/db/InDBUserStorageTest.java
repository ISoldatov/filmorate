package ru.yandex.practicum.filmorate.storage.db;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest // указываем, о необходимости подготовить бины для работы с БД
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class InDBUserStorageTest {

    private final JdbcTemplate jdbcTemplate;

    private static User firstUser;
    private static User secondUser;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {

        firstUser = User.builder()
                .id(null)
                .email("firstUser@email.ru")
                .login("firstUser")
                .birthday(LocalDate.of(2001, 1, 1))
                .friends(null)
                .build();

        secondUser = User.builder()
                .id(null)
                .email("secondUser@email.ru")
                .login("secondUser")
                .birthday(LocalDate.of(2002, 2, 2))
                .friends(null)
                .build();
    }

    @Test
    void saveAndGet() {
        UserStorage userStorage = new InDBUserStorage(jdbcTemplate);
        int idFirstUser = userStorage.save(firstUser).getId();

        User getUser = userStorage.get(idFirstUser);

        assertThat(getUser)
                .isNotNull() // проверяем, что объект не равен null
                .usingRecursiveComparison() // проверяем, что значения полей нового
                .isEqualTo(firstUser);        // и сохраненного пользователя - совпадают
    }

    @Test
    void update() {
        UserStorage userStorage = new InDBUserStorage(jdbcTemplate);
        int idFirstUser = userStorage.save(firstUser).getId();

        User updateUser = User.builder()
                .id(idFirstUser)
                .email("updateUser@email.ru")
                .login("updateUser")
                .name("updUser")
                .birthday(LocalDate.of(2005, 5, 5))
                .friends(null)
                .build();

        userStorage.update(updateUser);

        User updUser = userStorage.get(updateUser.getId());

        assertThat(updUser)
                .isNotNull() // проверяем, что объект не равен null
                .usingRecursiveComparison() // проверяем, что значения полей нового
                .isEqualTo(updateUser);        // и сохраненного пользователя - совпадают
    }

    @Test
    void delete() {
        UserStorage userStorage = new InDBUserStorage(jdbcTemplate);
        User[] users = new User[]{secondUser};
        int idFirstUser = userStorage.save(firstUser).getId();
        userStorage.save(secondUser);

        userStorage.delete(idFirstUser);
        User[] savedUsers = userStorage.getAll().toArray(new User[0]);


        assertEquals(1, savedUsers.length);
        assertArrayEquals(users, savedUsers);

    }

    @Test
    void getAll() {
        UserStorage userStorage = new InDBUserStorage(jdbcTemplate);
        User[] users = new User[]{firstUser, secondUser};

        userStorage.save(firstUser);
        userStorage.save(secondUser);

        User[] savedUsers = userStorage.getAll().toArray(new User[0]);

        assertEquals(2, savedUsers.length);
        assertArrayEquals(users, savedUsers);

    }
}