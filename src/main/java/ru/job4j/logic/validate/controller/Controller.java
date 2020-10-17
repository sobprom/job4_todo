package ru.job4j.logic.validate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    void doPost(HttpServletRequest req, HttpServletResponse resp);
    void doGet(HttpServletRequest req, HttpServletResponse resp);
}
