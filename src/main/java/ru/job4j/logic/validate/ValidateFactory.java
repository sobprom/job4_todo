package ru.job4j.logic.validate;

import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;

public class ValidateFactory {
    static Validate get(Message message) {
        if (message instanceof Item) {
            return ValidateItem.getInstance();
        }
        throw new UnsupportedOperationException();
    }
}
