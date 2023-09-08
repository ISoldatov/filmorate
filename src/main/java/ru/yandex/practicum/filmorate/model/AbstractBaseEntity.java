package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseEntity {

    protected Integer id;

    public boolean isNew() {
        return this.id == null;
    }
}
