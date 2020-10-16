package ru.job4j.logic.validate;

import ru.job4j.model.entities.Message;

import java.util.Collection;


public class ValidateService implements Validate {
    private static final Validate INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Message add(Message msg) {
        return ValidateFactory.get(msg).add(msg);
    }

    @Override
    public Message findByName(Message msg) {
        return ValidateFactory.get(msg).findByName(msg);
    }

    @Override
    public Message update(Message msg) {
        return ValidateFactory.get(msg).update(msg);
    }

    @Override
    public Message delete(Message msg) {
        return ValidateFactory.get(msg).delete(msg);
    }

    @Override
    public Collection<Message> findAll(Message msg) {
        return ValidateFactory.get(msg).findAll(msg);
    }

    @Override
    public Message findById(Message msg) {
        return ValidateFactory.get(msg).findById(msg);
    }
}
