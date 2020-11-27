package ru.job4j.logic.controller;

import ru.job4j.logic.dispatcher.Dispatcher;
import ru.job4j.logic.dispatcher.DispatcherImpl;
import ru.job4j.logic.json.Parser;
import ru.job4j.logic.json.UserParser;
import ru.job4j.logic.utils.Constants;
import ru.job4j.model.entities.Dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController implements Controller {
    private static final Controller INSTANCE = new UserController();
    private final Parser json = UserParser.getInstance();
    private final Dispatcher dispatcher = DispatcherImpl.getInstance();

    private UserController() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(
                json.get(req)
                        .addContext(Constants.REQUEST, req)
                        .addContext(Constants.RESPONSE, resp)
        );
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(
                new Dto()
                        .addContext(Constants.RESPONSE, resp)
                        .addContext(Constants.REQUEST, req)
                        .setAction(Constants.FIND_ALL)
        );
    }

    private void process(Dto dto) {
        if (dto.getErrorMsg().isEmpty()) {
            dto = dispatcher.apply(dto);
        }
        try {
            json.out(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
