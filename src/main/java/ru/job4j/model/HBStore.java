package ru.job4j.model;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.function.Function;

public class HBStore implements Store {
    private static final Store INSTANCE = new HBStore();


    private HBStore() {

    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Item add(Item item) {
        return tx(session -> {
            session.save(item);
            return item;
        });
    }

    @Override
    public Collection<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    @Override
    public Item findById(Item item) {
        return this.tx(
                session -> {
                    final Query<Item> query = session.createQuery("from Item as item where item.id = :id");
                    query.setParameter("id", item.getId());
                    return query.getSingleResult();
                }
        );
    }

    @Override
    public Item update(Item item) {
        return tx(session -> {
            session.update(item);
            return item;
        });
    }

    @Override
    public Item delete(Item item) {
        return tx(session -> {
            session.delete(item);
            return item;
        });
    }

    private <T> T tx(final Function<Session, T> command) {
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

    @Override
    public void close() {

    }
}
