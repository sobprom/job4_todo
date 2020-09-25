package ru.job4j.model;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;

public class HBStore implements Store {
    private static final Store INSTANCE = new HBStore();


    private HBStore() {

    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Item add(Item item) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction tx = session.beginTransaction();
            try {
                session.save(item);
                session.getTransaction().commit();
                session.close();
            } catch (Exception e) {
                item.setErrorMsg(e.getMessage());
                session.getTransaction().rollback();
            }
        }
        return item;
    }

    @Override
    public Collection<Item> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction tx = session.beginTransaction();
            try {
                Collection<Item> result = session.createQuery("from ru.job4j.model.Item").list();
                session.getTransaction().commit();
                session.close();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Item findById(Item item) {
        return null;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public Item delete(Item item) {
        return null;
    }

    @Override
    public void close() {

    }
}
