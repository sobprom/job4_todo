package ru.job4j.logic.validate;

import ru.job4j.logic.business.Logic;
import ru.job4j.logic.business.LogicImpl;
import ru.job4j.logic.utils.Constants;
import ru.job4j.logic.utils.ErrorCodes;
import ru.job4j.model.entities.Dto;
import ru.job4j.model.entities.User;

import javax.servlet.http.HttpServletRequest;


public class ValidateService implements Validate {
    private static final Validate INSTANCE = new ValidateService();
    private final Logic logic = LogicImpl.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public Dto add(Dto dto) {
        Dto rsl = validateItems(dto);
        return rsl.getErrorMsg().isEmpty() ? this.logic.add(dto) : rsl;
    }

    @Override
    public Dto update(Dto dto) {
        Dto rsl = validateItems(dto);
        return rsl.getErrorMsg().isEmpty() ? this.logic.update(dto) : rsl;
    }

    @Override
    public Dto findByName(Dto dto) {
        Dto rsl = validateUser(dto);
        return rsl.getErrorMsg().isEmpty()
                ? this.logic.findByName(dto)
                : dto;
    }


    @Override
    public Dto delete(Dto dto) {
        Dto rsl = validateItems(dto);
        return rsl.getErrorMsg().isEmpty() ? this.logic.delete(dto) : rsl;
    }

    @Override
    public Dto findById(Dto dto) {
        HttpServletRequest request = (HttpServletRequest) dto.getContext(Constants.REQUEST);
        return request.getSession().getAttribute(Constants.USER) != null
                ? this.logic.findById(dto)
                : dto.addErrorMsg(ErrorCodes.ERROR_WITH_SESSION);
    }

    @Override
    public Dto registration(Dto dto) {
        Dto rsl = validateUser(dto);
        return rsl.getErrorMsg().isEmpty()
                ? this.logic.registration(dto)
                : dto;
    }

    private Dto validateItems(Dto dto) {
        return dto.getUser().getItems().isEmpty()
                ? dto.addErrorMsg(ErrorCodes.EMPTY_ITEM_DESCRIPTION)
                : dto;
    }

    private Dto validateUser(Dto dto) {
        User user = dto.getUser();
        if (user == null) {
            return dto.addErrorMsg(ErrorCodes.INCORRECT_USER);
        }
        if (user.getName().isEmpty()) {
            dto.addErrorMsg(ErrorCodes.EMPTY_USER_NAME);
        }
        if (user.getPassword().isBlank()) {
            dto.addErrorMsg(ErrorCodes.EMPTY_USER_PASSWORD);
        }
        return dto;
    }
}
