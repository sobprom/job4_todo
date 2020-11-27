package ru.job4j.logic.dispatcher;

import ru.job4j.logic.auth.Auth;
import ru.job4j.logic.auth.AuthImpl;
import ru.job4j.logic.utils.Constants;
import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.utils.ErrorCodes;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.model.entities.Dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DispatcherImpl implements Dispatcher {
    private static final Dispatcher INSTANCE = new DispatcherImpl();
    private final Validate validate = ValidateService.getInstance();
    private final Auth auth = AuthImpl.getInstance();
    private final Map<String, Function<Dto, Dto>> dispatch = new ConcurrentHashMap<>();

    private DispatcherImpl() {
        init();
    }

    private void init() {
        this.load(Constants.UPDATE, validate::update);
        this.load(Constants.ADD, validate::add);
        this.load(Constants.DELETE, validate::delete);
        this.load(Constants.FIND_BY_NAME, validate::findByName);
        this.load(Constants.LOGIN, auth::login);
        this.load(Constants.LOGOUT, auth::logout);
        this.load(Constants.REGISTRATION, auth::registration);
        this.load(Constants.FIND_ALL, validate::findById);
    }

    public static Dispatcher getInstance() {
        return INSTANCE;
    }

    public void load(String type, Function<Dto, Dto> handle) {
        this.dispatch.put(type, handle);
    }

    public Map<String, Function<Dto, Dto>> getDispatch() {
        return dispatch;
    }

    @Override
    public Dto apply(Dto dto) {
        if (dto.getAction() != null && dispatch.containsKey(dto.getAction())) {
            return dispatch.get(dto.getAction()).apply(dto);
        }
        return dto.addErrorMsg(ErrorCodes.UNSUPPORTED_ACTION);
    }
}
