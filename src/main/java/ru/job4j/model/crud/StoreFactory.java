package ru.job4j.model.crud;

import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

public class StoreFactory {
    static Store get(Message msg) {
        if (msg instanceof Item) {
            return StoreItem.getInstance();
        } else if (msg instanceof Users) {
            return StoreUser.getInstance();
        }
        throw new UnsupportedOperationException();
    }
}
