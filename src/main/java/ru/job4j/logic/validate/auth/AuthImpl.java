package ru.job4j.logic.validate.auth;

import ru.job4j.model.entities.Message;

public class AuthImpl implements Auth {
    private static final Auth INSTANCE = new AuthImpl();

    private AuthImpl() {
    }

    public static Auth getInstance() {
        return INSTANCE;
    }

    @Override
    public Message auth(Message msg) {
        return null;
    }
}
