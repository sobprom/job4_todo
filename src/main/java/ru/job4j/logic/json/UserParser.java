package ru.job4j.logic.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.utils.Constants;
import ru.job4j.logic.utils.ErrorCodes;
import ru.job4j.model.entities.Dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserParser implements Parser {
    private static final Parser INSTANCE = new UserParser();

    private UserParser() {
    }

    public static Parser getInstance() {
        return INSTANCE;
    }

    @Override
    public Dto get(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), Dto.class);

        } catch (IOException e) {
            return new Dto().addErrorMsg(ErrorCodes.ERROR_JSON_PARSING);
        }
    }

    @Override
    public void out(Dto dto) throws IOException {
        HttpServletRequest req = (HttpServletRequest) dto.getContext(Constants.REQUEST);
        HttpServletResponse resp = (HttpServletResponse) dto.getContext(Constants.RESPONSE);
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        new ObjectMapper()
                .writeValue(
                        writer,
                        dto
                );
        writer.flush();
    }
}
