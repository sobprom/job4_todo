package ru.job4j.logic.validate.controller;

import ru.job4j.logic.validate.json.UserParser;
import ru.job4j.model.entities.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends AbstractController {
    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    private static final Controller INSTANCE = new UserController();

    private UserController() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Message user = UserParser.getInstance().apply(req);
        try {
            writeJsonObject(req, resp, user);
        } catch (IOException e) {
            logger.error("Error write json, stack trace = {}", e.getMessage());
        }
    }
}
