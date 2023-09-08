package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @Email(message = "Email не правильного формата.")
    private String email;

    @NotBlank(message = "Login не может быть пустым.")
    @Size(min = 3, max = 20, message = "Login должен быть от 3 до 20 символов.")
    private String login;

    private String name;

    @NotNull(message = "Дата рождения обязательна.")
    @Past(message = "Дата рождения не может быть в будущем.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

}
