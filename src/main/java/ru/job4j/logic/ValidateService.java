package ru.job4j.logic;

import ru.job4j.model.HBStore;
import ru.job4j.model.Item;
import ru.job4j.model.Store;

import java.util.Collection;

public class ValidateService implements Validate {
    private static final Validate INSTANCE = new ValidateService();
    private final Store store = HBStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Item add(Item item) {
        return store.add(item);
    }

    @Override
    public Collection<Item> findAll() {
        return store.findAll();
    }

    @Override
    public Item findById(Item item) {
        return store.findById(item);
    }

    @Override
    public Item update(Item item) {
        return store.update(item);
    }

    @Override
    public Item delete(Item item) {
        return store.delete(item);
    }
}
