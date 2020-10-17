package ru.job4j.logic.validate.json;

import ru.job4j.model.entities.Message;

import javax.servlet.http.HttpServletRequest;

public interface Parser {
    Message apply(HttpServletRequest request);
}
