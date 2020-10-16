package ru.job4j.model.crud;

import ru.job4j.model.entities.Message;

import java.util.Collection;

public class StoreItem extends AbstractStore {
    private static final Store INSTANCE = new StoreItem();
    private static final String TABLE_NAME = "Item";

    private StoreItem() {
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Message> findAll(Message msg) {
        return findAll(TABLE_NAME);
    }

    @Override
    public Message findById(Message msg) {
        return findById(TABLE_NAME, msg);
    }

    @Override
    public Message findByName(Message msg) {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
