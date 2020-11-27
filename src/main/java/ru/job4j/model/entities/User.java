package ru.job4j.model.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public User() {
    }

    public User(String name, Roles role) {
        this.name = name;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItems(List<Item> items) {
        items.forEach(item -> item.setUser(this));
        this.items.addAll(items);
    }

    public void update(List<Item> items) {
        items.forEach(it -> {
            Item item = this.items.get(findItem(it));
            it.setUser(this);
            item.update(it);
        });
    }

    private int findItem(Item item) {
        int rsl = -1;
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                rsl = i;
            }
        }
        return rsl;
    }
}
