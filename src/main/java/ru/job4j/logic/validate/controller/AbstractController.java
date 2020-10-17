package ru.job4j.logic.validate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.dispatcher.Dispatcher;
import ru.job4j.logic.dispatcher.DispatcherImpl;
import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.model.entities.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractController implements Controller {
    private final Dispatcher dispatcher = DispatcherImpl.getDispatcher();
    private final Validate validate = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }


    protected void writeJsonObject(HttpServletRequest request, HttpServletResponse response, Message msg) throws IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        new ObjectMapper()
                .writeValue(
                        writer,
                        dispatcher.apply(msg)
                );
        writer.flush();
    }
    protected void writeJsonCollection(HttpServletRequest request, HttpServletResponse response, Message msg) throws IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        new ObjectMapper()
                .writeValue(
                        writer,
                        validate.findAll(msg)
                );
        writer.flush();
    }
}
