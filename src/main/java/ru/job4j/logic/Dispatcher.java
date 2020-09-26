package ru.job4j.logic;

import ru.job4j.model.Item;

import java.util.Collection;

/**
 * Depending on the value of the request parameter "action" apply actions.
 */
public interface Dispatcher {
    Item crud(Item item);

    Collection<Item> findAll();
}
