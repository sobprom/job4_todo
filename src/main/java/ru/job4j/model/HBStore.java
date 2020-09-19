package ru.job4j.model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collection;

public class HBStore implements Store {
    private static final Store INSTANCE = new HBStore();
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private HBStore() {

    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Item add(Item item) {
        try (SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            item.setErrorMsg(e.getMessage());
        }
        return item;
    }

    @Override
    public Collection<Item> findAll() {
        return null;
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
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
