package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationUtil;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
public class UserService {

    private final UserStorage storage;

    @Autowired
    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public User create(User user) {
        return storage.save(user);
    }

    public User update(User user) {
        return ValidationUtil.checkNotFoundWithId(storage.save(user), user.getId());
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(storage.delete(id), id);
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(storage.get(id), id);
    }

    public List<User> getAll() {
        return storage.getAll();
    }

}
