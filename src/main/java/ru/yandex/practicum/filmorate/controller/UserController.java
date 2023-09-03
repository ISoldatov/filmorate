package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/users")
    public List<User> getAll() {
        log.debug("Запрос списка всех пользователей");
        return new ArrayList<>(users.values());
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        log.debug("Добавлен пользователь \"{}\"", user.getName());
        checkUser(user);
        if (user.getId() == null) {
            user.setId(counter.incrementAndGet());
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        return users.computeIfAbsent(user.getId(), v -> user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        int idUser = user.getId();
        log.debug("Обновлен пользователь с id={}", idUser);
        checkUser(user);
        if (!users.containsKey(idUser)) {
            throw new UserValidationException();
        }
        return users.computeIfPresent(idUser, (i, u) -> user);
    }

    private void checkUser(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@") ||
                user.getLogin().isBlank() || user.getBirthday().isAfter(LocalDate.now())) {
            throw new UserValidationException();
        }
    }
}
