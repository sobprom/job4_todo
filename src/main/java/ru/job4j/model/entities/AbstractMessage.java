package ru.job4j.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public abstract class AbstractMessage implements Message {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Transient
    private final List<String> errorMsg = new LinkedList<>();

    @Transient
    private String action;

    @Transient
    private final HashMap<String, Object> parameters = new HashMap<>();

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

    @Override
    public void setParameter(String name, Object obj) {
        this.parameters.put(name, obj);
    }

    @Override
    public Object getParameter(String name) {
        return parameters.get(name);
    }
}
