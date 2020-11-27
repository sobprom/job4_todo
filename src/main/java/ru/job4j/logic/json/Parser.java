package ru.job4j.logic.json;

import ru.job4j.model.entities.Dto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Parses request into the JSON and parses output dto to JSON  and write it to client.
 */
public interface Parser {
    /**
     * Parses request into the JSON.
     *
     * @param request from client.
     * @return dto with objects from JSON.
     */
    Dto get(HttpServletRequest request);

    /**
     * Parses output dto to JSON  and write it to client.
     *
     * @param dto source.
     * @throws IOException if exceptions.
     */
    void out(Dto dto) throws IOException;
}
