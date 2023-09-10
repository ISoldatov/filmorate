package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.yandex.practicum.filmorate.exception.ValidationUtil.*;

@Slf4j
@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        log.debug("Добавление пользователя \"{}\"", user.getName());
        checkNew(user);
        checkUser(user);
        return service.create(user);
    }

    @PutMapping("/users")
    public User update(@Valid @RequestBody User user) {
        log.debug("Обновление пользователя \"{}\"", user.getName());
        checkNotNew(user);
        checkUser(user);
        return service.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void delete(int id) {
        log.debug("Удаление пользователя с id={}", id);
        service.delete(id);
    }

    @GetMapping("/users/{id}")
    public User get(int id) {
        log.debug("Получение пользователя с id={}", id);
        return service.get(id);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        log.debug("Запрос списка всех пользователей");
        return service.getAll();
    }


}
