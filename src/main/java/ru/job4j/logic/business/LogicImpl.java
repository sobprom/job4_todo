package ru.job4j.logic.business;

import ru.job4j.logic.utils.Constants;
import ru.job4j.logic.utils.ErrorCodes;
import ru.job4j.model.entities.Dto;
import ru.job4j.model.entities.Item;
import ru.job4j.model.store.HBStore;
import ru.job4j.model.store.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LogicImpl implements Logic {
    private static final Logic INSTANCE = new LogicImpl();
    private final Store store = HBStore.getInstance();

    private LogicImpl() {

    }

    public static Logic getInstance() {
        return INSTANCE;
    }

    @Override
    public Dto add(Dto dto) {
        List<Item> added = dto.getUser().getItems();
        Dto current = findById(dto);
        current.getUser().addItems(added);
        Dto rsl = this.store.update(dto);
        rsl.getUser().getItems().clear();
        rsl.getUser().getItems().addAll(added);
        return rsl;
    }

    @Override
    public Dto update(Dto dto) {
        List<Item> updated = dto.getUser().getItems();
        Dto user = findById(dto);
        user.getUser().update(updated);
        Dto rsl = this.store.update(dto);
        rsl.getUser().getItems().clear();
        rsl.getUser().getItems().addAll(updated);
        return rsl;
    }

    @Override
    public Dto delete(Dto dto) {
        List<Item> updated = dto.getUser().getItems();
        Dto user = findById(dto);
        user.getUser().getItems().removeAll(updated);
        Dto rsl = this.store.update(user);
        rsl.getUser().getItems().clear();
        rsl.getUser().getItems().addAll(updated);
        return rsl;
    }

    @Override
    public Dto findByName(Dto dto) {
        return this.store.findByName(dto);
    }

    @Override
    public Dto findById(Dto dto) {
        return this.store.findById(getSessionUserId(dto));
    }

    @Override
    public Dto registration(Dto dto) {
        if (store.isUniqueName(dto)) {
            dto = store.findRoleByName(dto);
        }
        return dto.getErrorMsg().isEmpty()
                ? store.persist(dto)
                : dto.addErrorMsg(ErrorCodes.USER_IS_EXIST);
    }

    private Dto getSessionUserId(Dto dto) {
        HttpServletRequest request = (HttpServletRequest) dto.getContext(Constants.REQUEST);
        return dto.setId((Integer) request.getSession().getAttribute(Constants.USER));
    }
}
