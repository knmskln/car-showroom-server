package com.bsuir.server.repositories.impl;

import com.bsuir.server.entities.Order;
import com.bsuir.server.repositories.OrderRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.util.config.AppConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OrderJpaRepository implements OrderRepository {

    private static final OrderJpaRepository INSTANCE = new OrderJpaRepository();

    public static OrderJpaRepository getInstance() {
        return INSTANCE;
    }

    private OrderJpaRepository() {
    }

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<Order> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order", Order.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Order get(int orderId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order WHERE orderId = ?1", Order.class)
                    .setParameter(1, orderId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public void add(Order order) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Order order) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Order order = session.createQuery("FROM Order WHERE orderId = ?1", Order.class)
                    .setParameter(1, id)
                    .uniqueResult();
            session.delete(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Order> getOrderByUserId(int userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order WHERE userId.userId = ?1", Order.class)
                    .setParameter(1, userId)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
    @Override
    public List<Order> getOrderByStatusId(int statusId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order WHERE orderStatus.statusId = ?1", Order.class)
                    .setParameter(1, statusId)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}
