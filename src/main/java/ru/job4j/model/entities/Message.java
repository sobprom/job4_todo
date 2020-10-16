package ru.job4j.model.entities;

public interface Message {
    Message setErrorMsg(String msg);

    String getErrorMsg();

    int getId();

    String getAction();
}
