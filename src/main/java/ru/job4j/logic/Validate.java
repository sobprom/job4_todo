package ru.job4j.logic;


import ru.job4j.model.Item;

import java.util.Collection;

/**
 * Logic layer validates required fields before to put it to store.
 */
public interface Validate {
    /**
     * Adds item to store.
     *
     * @param item model.
     * @return if validate is success then returns stored model, else returns null.
     */
    Item add(Item item);

    /**
     * Finds all items.
     *
     * @return list of all items.
     */
    Collection<Item> findAll();

    /**
     * Finds model by id.
     *
     * @param item item.
     * @return item was founded.
     */
    Item findById(Item item);

    /**
     * Updates the item in store. If not exists then adds to store.
     *
     * @param item new item.
     * @return model was updated.
     */
    Item update(Item item);

    /**
     * Deletes the item from store.
     *
     * @param item item.
     * @return item was deleted. If not exists return null.
     */
    Item delete(Item item);
}
