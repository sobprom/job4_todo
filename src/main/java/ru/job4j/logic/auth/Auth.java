package ru.job4j.logic.auth;

import ru.job4j.model.entities.Dto;

/**
 * Actions with authorization.
 */
public interface Auth {
    Dto login(Dto dto);

    Dto logout(Dto dto);

    Dto registration(Dto dto);
}
