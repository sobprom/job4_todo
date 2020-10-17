package ru.job4j.logic.dispatcher;

import ru.job4j.logic.validate.Validate;
import ru.job4j.logic.validate.ValidateService;
import ru.job4j.logic.validate.auth.Auth;
import ru.job4j.logic.validate.auth.AuthImpl;
import ru.job4j.model.entities.Message;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DispatcherImpl implements Dispatcher {
    private static final Dispatcher DISPATCHER = new DispatcherImpl();
    private final Validate validate = ValidateService.getInstance();
    private final Auth auth = AuthImpl.getInstance();
    private final Map<String, Function<Message, Message>> dispatch = new ConcurrentHashMap<>();

    private DispatcherImpl() {
        init();
    }

    private void init() {
        this.load("update", validate::update);
        this.load("add", validate::add);
        this.load("delete", validate::delete);
        this.load("findByName", validate::findByName);
        this.load("findById", validate::findById);
        this.load("auth", auth::auth);
    }

    public static Dispatcher getDispatcher() {
        return DISPATCHER;
    }

    public void load(String type, Function<Message, Message> handle) {
        this.dispatch.put(type, handle);
    }

    public Map<String, Function<Message, Message>> getDispatch() {
        return dispatch;
    }

    @Override
    public Message apply(Message message) {
        if (getDispatch().containsKey(message.getAction())) {
            return getDispatch().get(message.getAction()).apply(message);
        }
        throw new NoSuchElementException();
    }
}
