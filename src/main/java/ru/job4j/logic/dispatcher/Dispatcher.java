package ru.job4j.logic.dispatcher;

import ru.job4j.model.entities.Message;


/**
 * Depending on the value of the request parameter "action" apply actions.
 */
public interface Dispatcher {
    Message apply(Message message);
}
