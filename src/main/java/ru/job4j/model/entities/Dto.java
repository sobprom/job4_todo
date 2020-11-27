package ru.job4j.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Dto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final List<String> errorMsg = new LinkedList<>();
    private int id;
    private User user;
    private String action;
    private String roleName;
    private final HashMap<String, Object> context = new HashMap<>();

    public Dto addErrorMsg(String msg) {
        this.errorMsg.add(msg);
        return this;
    }

    public String getErrorMsg() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        errorMsg.forEach(rsl::add);
        return errorMsg.size() > 0 ? rsl.toString() : "";
    }

    public String getAction() {
        return this.action;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Dto addContext(String name, Object obj) {
        this.context.put(name, obj);
        return this;
    }


    public Object getContext(String name) {
        return this.context.get(name);
    }

    public Dto setUser(User user) {
        this.user = user;
        return this;
    }

    public Dto setAction(String action) {
        this.action = action;
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public int getId() {
        return id;
    }

    public Dto setId(int id) {
        this.id = id;
        return this;
    }
}
