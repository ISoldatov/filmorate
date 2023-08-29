package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public List<User> getAll() {
        log.debug("Запрос списка всех пользователей");
        return new ArrayList<>(users.values());
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        log.debug("Добавлен пользователь \"{}\"", user.getName());
        return users.putIfAbsent(user.getId(), user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        int idUser = user.getId();
        log.debug("Обновлен пользователь с id={}", idUser);
        return users.computeIfPresent(idUser, (i, u) -> user);
    }
}
