package ru.job4j.logic.validate;

import ru.job4j.logic.validate.utils.ErrorCodes;
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
                ? item.setErrorMsg(ErrorCodes.EMPTY_ITEM_DESCRIPTION)
                : getStore().add(msg);
    }

    @Override
    public Message update(Message msg) {
        Item item = (Item) msg;
        return item.getDescription() == null
                || item.getDescription().isBlank()
                || item.getId() < 0
                ? item.setErrorMsg(ErrorCodes.EMPTY_ITEM_DESCRIPTION)
                : getStore().update(msg);
    }

    @Override
    public Message findByName(Message msg) {
        return msg.setErrorMsg(ErrorCodes.UNSUPPORTED_ACTION);
    }
}
