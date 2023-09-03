package ru.yandex.practicum.filmorate;

import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class UserTestData {

    public static final User userNotIdNotName = new User("email_user1@ya.ru", "login_user1", LocalDate.of(1985, 1, 1));
    public static final User userNotIdHasName = new User("email_user2@ya.ru", "login_user2", "name_user2 ", LocalDate.of(1985, 2, 2));

    public static final User userHasId_1_NotName = new User(1, "email_user_1@ya.ru", "login_user_1", LocalDate.of(1985, 1, 1));
    public static final User userHasId_2_HasName = new User(2, "email_user_2@ya.ru", "login_user_2", "name_user2", LocalDate.of(1985, 2, 2));

}
