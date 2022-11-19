package com.bsuir.server.repositories.impl;

import com.bsuir.server.entities.Role;
import com.bsuir.server.entities.Status;
import com.bsuir.server.repositories.StatusRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.util.config.AppConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StatusJpaRepository implements StatusRepository {
    private static final StatusJpaRepository INSTANCE = new StatusJpaRepository();

    public static StatusJpaRepository getInstance() {
        return INSTANCE;
    }

    private StatusJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Status get(int statusId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Status WHERE statusId = ?1", Status.class)
                    .setParameter(1, statusId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}
