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
        User[] usersExpected = new User[]{userNotIdNotName_1, userNotIdHasName_2};
        userController.create(userNotIdNotName_1);
        userController.create(userNotIdHasName_2);
        assertArrayEquals(usersExpected, userController.getAll().toArray());
    }

    @Test
    void createNormal() {
        User testUser_1 = userController.create(userNotIdNotName_1);
        assertEquals(userNotIdNotName_1, testUser_1);
        User testUser_2 = userController.create(userNotIdHasName_2);
        assertEquals(userNotIdHasName_2, testUser_2);
    }

    @Test
    void createNotValidFields() {
        assertThrows(UserValidationException.class, () -> userController.create(userBadEmail));
        assertThrows(UserValidationException.class, () -> userController.create(userBadLogin));
        assertThrows(UserValidationException.class, () -> userController.create(userBadBirthday));
    }

    @Test
    void update() {
        userController.create(userNotIdNotName_1);
        User testUser_1 = userController.update(userHasId_1_NotName);
        assertEquals(userHasId_1_NotName, testUser_1);
    }
}