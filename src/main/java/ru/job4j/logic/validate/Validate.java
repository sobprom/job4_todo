package ru.job4j.logic.validate;


import ru.job4j.model.entities.Message;

import java.util.Collection;

/**
 * Logic layer validates required fields before to put it to store.
 */
public interface Validate {
    /**
     * Adds msg to store.
     *
     * @param msg model.
     * @return if validate is success then returns stored model, else returns null.
     */
    Message add(Message msg);


    /**
     * Finds model by id.
     *
     * @param msg msg.
     * @return msg was founded.
     */
    Message findByName(Message msg);

    /**
     * Updates the item in store. If not exists then adds to store.
     *
     * @param msg new item.
     * @return model was updated.
     */
    Message update(Message msg);

    /**
     * Deletes the item from store.
     *
     * @param msg item.
     * @return item was deleted. If not exists return null.
     */
    Message delete(Message msg);

    Collection<Message> findAll(Message msg);

    Message findById(Message msg);
}
