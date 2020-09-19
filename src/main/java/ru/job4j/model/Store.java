package ru.job4j.model;

import java.util.Collection;

public interface Store extends AutoCloseable {
    /**
     * Adds item to store.
     *
     * @param entity model.
     * @return stored model.
     */
    Item add(Item item);

    /**
     * Finds all items.
     *
     * @return list of all models.
     */
    Collection<Item> findAll();

    /**
     * Finds item by id.
     *
     * @param item item
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
     * Deletes the model from store.
     *
     * @param item item.
     * @return model was deleted. If not exists return null.
     */
    Item delete(Item item);
}
