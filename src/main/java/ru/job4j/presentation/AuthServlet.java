package ru.job4j.presentation;

import ru.job4j.logic.controller.Controller;
import ru.job4j.logic.controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {
    private final Controller controller = UserController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        controller.doPost(req, resp);
    }
}
