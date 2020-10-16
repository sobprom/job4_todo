package ru.job4j.logic.validate;

import ru.job4j.model.crud.HBStore;
import ru.job4j.model.crud.Store;
import ru.job4j.model.entities.Message;

import java.util.Collection;

public abstract class AbstractValidate implements Validate {

    private final Store store = HBStore.getInstance();

    public Store getStore() {
        return store;
    }

    @Override
    public Message delete(Message msg) {
        Message rsl = findById(msg);
        return rsl.getErrorMsg() != null ? rsl : store.delete(msg);
    }

    @Override
    public Collection<Message> findAll(Message msg) {
        return store.findAll(msg);
    }

    @Override
    public Message findById(Message msg) {
        return msg.getId() < 0
                ? msg.setErrorMsg(ErrorCodes.ILLEGAL_ID)
                : store.findById(msg);
    }
}
