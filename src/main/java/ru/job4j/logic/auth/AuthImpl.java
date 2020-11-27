package ru.job4j.logic.auth;

import ru.job4j.logic.utils.Constants;
import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.model.entities.Dto;

import javax.servlet.http.HttpServletRequest;

public class AuthImpl implements Auth {
    private static final Auth INSTANCE = new AuthImpl();
    private final Validate validate = ValidateService.getInstance();

    private AuthImpl() {
    }

    public static Auth getInstance() {
        return INSTANCE;
    }

    @Override
    public Dto login(Dto dto) {
        Dto rsl = validate.findByName(dto);
        if (rsl.getErrorMsg().isEmpty()) {
            HttpServletRequest request = (HttpServletRequest) dto.getContext(Constants.REQUEST);
            request.getSession().setAttribute(Constants.USER, rsl.getUser().getId());
        }
        return rsl;
    }

    @Override
    public Dto logout(Dto dto) {
        HttpServletRequest request = (HttpServletRequest) dto.getContext(Constants.REQUEST);
        request.getSession().removeAttribute(Constants.USER);
        return dto;
    }

    @Override
    public Dto registration(Dto dto) {
        dto.setRoleName(Constants.DEFAULT_ROLE);
        Dto rsl = validate.registration(dto);

        if (rsl.getErrorMsg().isEmpty()) {
            HttpServletRequest request = (HttpServletRequest) dto.getContext(Constants.REQUEST);
            request.getSession().setAttribute(Constants.USER, rsl.getUser().getId());
        }
        return rsl;
    }
}
