package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

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
    public User get(@PathVariable int id) {
        log.debug("Получение пользователя с id={}", id);
        return service.get(id);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        log.debug("Запрос списка всех пользователей");
        return service.getAll();
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable int id, @PathVariable int friendId) {
        log.debug("Добавление в друзья пользователя id={} друга с friendId={}", id, friendId);
        service.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable int id, @PathVariable int friendId) {
        log.debug("Удаление из друзей пользователя id={} друга с friendId={}", id, friendId);
        service.removeFriend(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        log.debug("Получение списка друзей пользователя id={}", id);
        return service.getFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> getCommFriends(@PathVariable int id, @PathVariable int otherId) {
        log.debug("Получение списка общих друзей пользователя id={} с пользователем otherId={}", id, otherId);
        return service.getCommFriends(id, otherId);
    }

}
