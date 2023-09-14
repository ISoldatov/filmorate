package ru.yandex.practicum.filmorate;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@UtilityClass
public class UserTestData {

    private static final LocalDate BIRTHDAY = LocalDate.of(1985, 3, 29);

    public static final User user = new User(1, "email@ya.ru", "Login", "Name", BIRTHDAY);
    public static final User userNotName = new User(1, "email@ya.ru", "Login", BIRTHDAY);
    public static final User userNotId = User.builder()
            .email("email@ya.ru")
            .login("Login")
            .name("Name")
            .birthday(BIRTHDAY)
            .build();

    public static final User userNotId_2 = User.builder()
            .email("email_2@ya.ru")
            .login("Login_2")
            .name("Name_2")
            .birthday(BIRTHDAY)
            .build();

    public static final User userNotIdNotName = User.builder()
            .email("email@ya.ru")
            .login("Login")
            .birthday(BIRTHDAY)
            .build();

    public static final User userBadEmail = new User("email-ya.ru", "Login", BIRTHDAY);
    public static final User userBadLogin = new User("email@ya.ru", "", BIRTHDAY);
    public static final User userBadBirthday = new User("email@ya.ru", "Login", LocalDate.of(3000, 1, 1));

}
