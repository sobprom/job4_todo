package ru.job4j.model.crud;

import org.hibernate.query.Query;
import ru.job4j.model.entities.Message;
import ru.job4j.model.entities.Users;

import java.util.Collection;

public class StoreUser extends AbstractStore {
    private static final Store INSTANCE = new StoreUser();
    private static final String TABLE_NAME = "Users";

    private StoreUser() {
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Message> findAll(Message msg) {
        return findAll(TABLE_NAME);
    }

    @Override
    public Message findById(Message msg) {
        return findById(TABLE_NAME, msg);
    }

    @Override
    public Message findByName(Message msg) {
        return this.tx(
                session -> {
                    final Query<Users> query = session.createQuery(
                            "from Users as usr where usr.name = :name"
                    );
                    query.setParameter("name",  ((Users) msg).getName());
                    return query.getSingleResult();
                }
        );
    }
}
