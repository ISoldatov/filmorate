package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.filmorate.UserTestData.*;

class UserControllerTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    void getAll() {
        User[] usersExpected = new User[]{userNotId, userNotId_2};
        userController.create(userNotId);
        userController.create(userNotId_2);
        assertArrayEquals(usersExpected, userController.getAll().toArray());
    }

    @Test
    void createNotId() {
        User testUser2 = userController.create(userNotId);
        assertEquals(userNotId, testUser2);
    }

    @Test
    void createNotIdNotName() {
        User testUser1 = userController.create(userNotIdNotName);
        assertEquals(userNotIdNotName, testUser1);
    }

    @Test
    void createNotValidFields() {
        assertThrows(UserValidationException.class, () -> userController.create(userBadEmail));
        assertThrows(UserValidationException.class, () -> userController.create(userBadLogin));
        assertThrows(UserValidationException.class, () -> userController.create(userBadBirthday));
    }

    @Test
    void update() {
        userController.create(userNotIdNotName);
        User testUser1 = userController.update(userNotName);
        assertEquals(userNotName, testUser1);
    }
}