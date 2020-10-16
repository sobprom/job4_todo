package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.dispatcher.Dispatcher;
import ru.job4j.logic.dispatcher.DispatcherImpl;
import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TodoServlet extends HttpServlet {
    private final Dispatcher dispatcher = DispatcherImpl.getDispatcher();
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        Message item = new Item();
        new ObjectMapper()
                .writeValue(
                        writer,
                        validate.findAll(item)
                );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        Message item = new ObjectMapper().readValue(req.getInputStream(), Item.class);
        new ObjectMapper()
                .writeValue(
                        writer,
                        dispatcher.apply(item)
                );
        writer.flush();
    }
}
