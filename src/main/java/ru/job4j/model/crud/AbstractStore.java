package ru.job4j.model.crud;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.logic.validate.utils.ErrorCodes;
import ru.job4j.model.entities.Item;
import ru.job4j.model.entities.Message;
import ru.job4j.model.utils.HibernateUtil;

import java.util.Collection;
import java.util.function.Function;

public abstract class AbstractStore implements Store {
    @Override
    public Message add(Message msg) {
        return tx(session -> {
            session.save(msg);
            return msg;
        });
    }

    @Override
    public Message update(Message msg) {
        return tx(session -> {
            session.update(msg);
            return msg;
        });
    }

    @Override
    public Message delete(Message msg) {
        return tx(session -> {
            session.delete(msg);
            return msg;
        });
    }

    protected Message findById(String table, Message msg) {
        return this.tx(
                session -> {
                    final Query<Item> query = session.createQuery(
                            String.format("from %s as tbl where tbl.id = :id", table)
                    );
                    query.setParameter("id", msg.getId());
                    try {
                        return query.getSingleResult();
                    } catch (Exception e) {
                        return msg.setErrorMsg(ErrorCodes.ILLEGAL_ID);
                    }
                }
        );
    }


    protected Collection<Message> findAll(String table) {
        return this.tx(
                session -> session.createQuery(
                        String.format("from %s", table)).list()
        );
    }

    protected <T> T tx(final Function<Session, T> command) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction tx = session.beginTransaction();
            try {
                T rsl = command.apply(session);
                tx.commit();
                return rsl;
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
