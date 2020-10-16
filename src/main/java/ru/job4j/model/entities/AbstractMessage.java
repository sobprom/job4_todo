package ru.job4j.model.entities;


import javax.persistence.Transient;

public abstract class AbstractMessage implements Message {
    @Transient
    private String errorMsg;

    @Transient
    private String action;

    @Override
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Message setErrorMsg(String msg) {
        this.errorMsg = msg;
        return this;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
