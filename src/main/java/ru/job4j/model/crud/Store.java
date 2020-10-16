package ru.job4j.model.crud;

import ru.job4j.model.entities.Message;

import java.util.Collection;

public interface Store  {
    /**
     * Adds item to store.
     *
     * @param msg new item.
     * @return stored item.
     */
    Message add(Message msg);

    /**
     * Finds all items.
     *
     * @return list of all models.
     */
    Collection<Message> findAll(Message msg);

    /**
     * Finds item by id.
     *
     * @param msg item
     * @return msg was founded.
     */
    Message findById(Message msg);

    Message findByName(Message msg);

    /**
     * Updates the item in store. If not exists then adds to store.
     *
     * @param msg new item.
     * @return msg was updated.
     */
    Message update(Message msg);

    /**
     * Deletes the item from store.
     *
     * @param msg item.
     * @return msg was deleted. If not exists return null.
     */
    Message delete(Message msg);

}
