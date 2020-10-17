package ru.job4j.model.entities;


import javax.persistence.Transient;
import java.util.List;
import java.util.StringJoiner;

public abstract class AbstractMessage implements Message {
    @Transient
    private List<String> errorMsg;

    @Transient
    private String action;

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public Message setErrorMsg(String msg) {
        this.errorMsg.add(msg);
        return this;
    }

    @Override
    public String getErrorMsg() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        errorMsg.forEach(rsl::add);
        return errorMsg.size() > 0 ? rsl.toString() : "";
    }
}
