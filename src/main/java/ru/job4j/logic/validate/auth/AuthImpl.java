package ru.job4j.logic.validate.auth;

import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

import javax.servlet.http.HttpSession;

public class AuthImpl implements Auth {
    private static final Auth INSTANCE = new AuthImpl();
    private Validate validate = ValidateService.getInstance();

    private AuthImpl() {
    }

    public static Auth getInstance() {
        return INSTANCE;
    }

    @Override
    public Message auth(Message msg) {
        Users user = (Users) validate.findByName(msg);
        if (user.getErrorMsg() == null) {
            HttpSession sc = (HttpSession) user.getParameter("session");
            sc.setAttribute("user", user.getName());
        }
        return user;
    }
}
