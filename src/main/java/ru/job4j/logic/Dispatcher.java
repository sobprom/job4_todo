package ru.job4j.logic;

import ru.job4j.model.Item;


/**
 * Depending on the value of the request parameter "action" apply actions.
 */
public interface Dispatcher {
    Item apply(Item item);
}
