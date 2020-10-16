package ru.job4j.logic.validate;

import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;


public class ValidateItem extends AbstractValidate {
    private static final Validate INSTANCE = new ValidateItem();


    private ValidateItem() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Message add(Message msg) {
        Item item = (Item) msg;
        return item.getDescription() == null || item.getDescription().isBlank()
                ? item.setErrorMsg("Empty description")
                : getStore().add(msg);
    }

    @Override
    public Message update(Message msg) {
        Item item = (Item) msg;
        return item.getDescription() == null
                || item.getDescription().isBlank()
                || item.getId() < 0
                ? item.setErrorMsg("Empty description or illegal id")
                : getStore().update(msg);
    }

    @Override
    public Message findByName(Message msg) {
        throw new UnsupportedOperationException("Not supported");
    }

}
