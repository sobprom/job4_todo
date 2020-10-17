package ru.job4j.logic.validate.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.validate.utils.ErrorCodes;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserParser implements Parser {
    private static final Parser INSTANCE = new UserParser();

    private UserParser() {
    }

    public static Parser getInstance() {
        return INSTANCE;
    }

    @Override
    public Message apply(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), Users.class);
        } catch (IOException e) {
            return new Users().setErrorMsg(ErrorCodes.ERROR_JSON_PARSING);
        }
    }
}
