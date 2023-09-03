package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.UserTestData;
import ru.yandex.practicum.filmorate.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.filmorate.UserTestData.userNotIdHasName;
import static ru.yandex.practicum.filmorate.UserTestData.userNotIdNotName;

class UserControllerTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    void getAll() {
        User[] usersExpected = new User[]{userNotIdNotName, userNotIdHasName};
        userController.create(userNotIdNotName);
        userController.create(userNotIdHasName);
        assertArrayEquals(usersExpected, userController.getAll().toArray());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}