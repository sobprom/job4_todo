package ru.job4j.model.store;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.logic.utils.ErrorCodes;
import ru.job4j.model.entities.Dto;
import ru.job4j.model.entities.Roles;
import ru.job4j.model.entities.User;
import ru.job4j.model.utils.HibernateUtil;

import java.util.function.Function;

public class HBStore implements Store {
    private static final Store INSTANCE = new HBStore();

    public static Store getInstance() {
        return INSTANCE;
    }

    private HBStore() {

    }

    @Override
    public Dto persist(Dto dto) {
        return tx(session -> {
            session.persist(dto.getUser());
            return dto;
        });
    }

    @Override
    public Dto findById(Dto dto) {
        return this.tx(
                session -> dto.setUser(session.find(User.class, dto.getId()))
        );
    }

    @Override
    public Dto findByName(Dto dto) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "from User as usr "
                                    + "where usr.name = :name "
                                    + "and usr.password = :password"
                    );
                    query.setParameter("name", dto.getUser().getName());
                    query.setParameter("password", dto.getUser().getPassword());
                    try {
                        return dto.setUser((User) query.getSingleResult());
                    } catch (Exception e) {
                        return dto.addErrorMsg(ErrorCodes.USER_NO_FINDS);
                    }
                }
        );
    }

    @Override
    public Boolean isUniqueName(Dto dto) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "from User as usr "
                                    + "where usr.name = :name "

                    );
                    query.setParameter("name", dto.getUser().getName());
                    try {
                        return query.getResultList().size() == 0;
                    } catch (Exception e) {
                        return true;
                    }
                }
        );
    }

    @Override
    public Dto update(Dto dto) {
        return tx(session -> {
            session.update(dto.getUser());
            return dto;
        });
    }

    @Override
    public Dto delete(Dto dto) {
        return tx(session -> {
            session.delete(dto);
            return dto;
        });
    }

    @Override
    public Dto findRoleByName(Dto dto) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "from Roles as rls "
                                    + "where rls.name = :name "
                    );
                    query.setParameter("name", dto.getRoleName());
                    try {
                        dto.getUser().setRole((Roles) query.getSingleResult());
                        return dto;
                    } catch (Exception e) {
                        return dto.addErrorMsg(ErrorCodes.USER_NO_FINDS);
                    }
                }
        );
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
}
