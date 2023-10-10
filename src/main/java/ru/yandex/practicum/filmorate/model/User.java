package ru.yandex.practicum.filmorate.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractBaseEntity {
//    @Email(message = "Email не верного формата.")
    private String email;

    @NotBlank(message = "Login не может быть пустым.")
    @Size(min = 3, max = 20, message = "Login должен быть от 3 до 20 символов.")
    private String login;

    private String name;

    @NotNull(message = "Дата рождения обязательна.")
    @PastOrPresent(message = "Дата рождения не может быть в будущем.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Set<Integer> friends = new HashSet<>();

    public User(Integer id, String email, String login, LocalDate birthday) {
        this(id, email, birthday);
        this.login = login;
    }

    public User(Integer id, String email, String login, String name, LocalDate birthday) {
        this(id, email, login, birthday);
        this.name = name;
    }

    public User(Integer id, String email, LocalDate birthday) {
        super(id);
        this.email = email;
        this.birthday = birthday;
    }

    public User(String email, String login, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.birthday = birthday;
    }

}
