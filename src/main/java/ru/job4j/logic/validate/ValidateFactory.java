package ru.job4j.logic.validate;

import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

public class ValidateFactory {
    static Validate get(Message message) {
        if (message instanceof Item) {
            return ValidateItem.getInstance();
        } else if (message instanceof Users) {
            return ValidateUser.getInstance();
        }
        throw new UnsupportedOperationException();
    }
}
