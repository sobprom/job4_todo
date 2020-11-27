package ru.job4j.logic.business;

import ru.job4j.model.entities.Dto;

/**
 * Business logic with dto before storing it in storage.
 */
public interface Logic {
    Dto add(Dto dto);

    Dto update(Dto dto);

    Dto delete(Dto dto);

    Dto findByName(Dto dto);

    Dto findById(Dto dto);

    Dto registration(Dto dto);
}
