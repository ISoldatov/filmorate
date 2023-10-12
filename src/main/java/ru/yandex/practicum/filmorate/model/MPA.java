package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class MPA{
private final int id;
private String title;

    public MPA(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public MPA(int id) {
        this.id = id;
    }
}
