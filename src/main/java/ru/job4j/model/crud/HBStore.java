package ru.job4j.model.crud;


import ru.job4j.model.entities.Message;

import java.util.Collection;

public class HBStore implements Store {
    private static final Store INSTANCE = new HBStore();


    private HBStore() {

    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Message add(Message msg) {
        return StoreFactory.get(msg).add(msg);
    }

    @Override
    public Collection<Message> findAll(Message msg) {
        return StoreFactory.get(msg).findAll(msg);
    }

    @Override
    public Message findById(Message msg) {
        return StoreFactory.get(msg).findById(msg);
    }

    @Override
    public Message findByName(Message msg) {
        return StoreFactory.get(msg).findByName(msg);
    }

    @Override
    public Message update(Message msg) {
        return StoreFactory.get(msg).update(msg);
    }

    @Override
    public Message delete(Message msg) {
        return StoreFactory.get(msg).delete(msg);
    }
}
