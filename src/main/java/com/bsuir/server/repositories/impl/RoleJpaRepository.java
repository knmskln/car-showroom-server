package com.bsuir.server.repositories.impl;

import com.bsuir.server.entities.Role;
import com.bsuir.server.repositories.RoleRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.util.config.AppConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoleJpaRepository implements RoleRepository {
    private static final RoleJpaRepository INSTANCE = new RoleJpaRepository();

    public static RoleJpaRepository getInstance() {
        return INSTANCE;
    }

    private RoleJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Role get(int userStatusId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE roleId = ?1", Role.class)
                    .setParameter(1, userStatusId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}
