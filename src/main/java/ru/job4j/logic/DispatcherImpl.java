package ru.job4j.logic;

import ru.job4j.model.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DispatcherImpl implements Dispatcher {
    private static final Dispatcher DISPATCHER = new DispatcherImpl();
    private final Validate validate = ValidateService.getInstance();
    private final Map<String, Function<Item, Item>> dispatch = new ConcurrentHashMap<>();

    private DispatcherImpl() {
        init();
    }

    private void init() {
        this.load("update", validate::update);
        this.load("add", validate::add);
        this.load("delete", validate::delete);
        this.load("findById", validate::findById);
    }

    public static Dispatcher getDispatcher() {
        return DISPATCHER;
    }

    public void load(String type, Function<Item, Item> handle) {
        this.dispatch.put(type, handle);
    }

    public Map<String, Function<Item, Item>> getDispatch() {
        return dispatch;
    }

    @Override
    public Item apply(Item item) {
        Item rsl = new Item();
        if (getDispatch().get(item.getAction()) != null) {
            rsl = getDispatch().get(item.getAction()).apply(item);
        } else {
            rsl.setErrorMsg("Illegal action name");
        }
        return rsl;
    }
}
