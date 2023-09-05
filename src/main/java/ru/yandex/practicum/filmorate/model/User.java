package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;

    public User(String email, String login, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.birthday = birthday;
    }

    public User(Integer id, String email, String login, LocalDate birthday) {
        this(email, login, birthday);
        this.id = id;
    }

    public User(String email, String login, String name, LocalDate birthday) {
        this(email, login, birthday);
        this.name = name;
    }


//    public User(Integer id, String email, String login, String name, LocalDate birthday) {
//        this(id, email, login, birthday);
//        this.name = name;
//    }


}
