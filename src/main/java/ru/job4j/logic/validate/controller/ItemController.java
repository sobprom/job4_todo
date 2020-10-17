package ru.job4j.logic.validate.controller;

import ru.job4j.logic.validate.json.ItemParser;
import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemController extends AbstractController {
    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ItemController.class);
    private static final Controller INSTANCE = new ItemController();

    private ItemController() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Message item = new Item();
        try {
            writeJsonCollection(req, resp, item);
        } catch (IOException e) {
            logger.error("Error write json, stack trace = {}", e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Message item = ItemParser.getInstance().apply(req);
        try {
            writeJsonObject(req, resp, item);
        } catch (IOException e) {
            logger.error("Error write json, stack trace = {}", e.getMessage());
        }

    }
}
