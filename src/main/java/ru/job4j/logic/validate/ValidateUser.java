package ru.job4j.logic.validate;

import ru.job4j.logic.validate.utils.ErrorCodes;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

public class ValidateUser extends AbstractValidate {
    private ValidateUser() {
    }

    private static final Validate INSTANCE = new ValidateUser();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Message add(Message msg) {
        return null;
    }

    @Override
    public Message findByName(Message msg) {
        Users user = (Users) msg;
        if (user.getName() == null || user.getName().isBlank()) {
            msg.setErrorMsg(ErrorCodes.EMPTY_USER_NAME);
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            msg.setErrorMsg(ErrorCodes.EMPTY_USER_PASSWORD);
        }
        return msg.getErrorMsg().isBlank()
                ? getStore().findByName(msg)
                : msg;
    }

    @Override
    public Message update(Message msg) {
        return null;
    }
}
