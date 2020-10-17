package ru.job4j.logic.validate.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.validate.utils.ErrorCodes;
import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ItemParser implements Parser {
    private static final Parser INSTANCE = new ItemParser();

    private ItemParser() {
    }

    public static Parser getInstance() {
        return INSTANCE;
    }

    @Override
    public Message apply(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), Item.class);
        } catch (IOException e) {
            return new Item().setErrorMsg(ErrorCodes.ERROR_JSON_PARSING);
        }
    }
}
