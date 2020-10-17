package ru.job4j.presentation;

import ru.job4j.logic.validate.controller.Controller;
import ru.job4j.logic.validate.controller.ItemController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodoServlet extends HttpServlet {
    private final Controller controller = ItemController.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        controller.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        controller.doPost(req, resp);
    }
}
