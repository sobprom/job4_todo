package ru.job4j.logic.validate.auth;

import ru.job4j.model.entities.Message;

public interface Auth {
    Message auth(Message msg);
}
