package com.bsuir.server.repositories;
import com.bsuir.server.entities.Order;
import com.bsuir.server.repositories.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> getAll() throws RepositoryException;

    Order get(int orderId) throws RepositoryException;

    void add(Order order) throws RepositoryException;

    void update(Order order) throws RepositoryException;

    void delete(int orderId) throws RepositoryException;

    List<Order> getOrderByUserId(int userId) throws RepositoryException;

    List<Order> getOrderByStatusId(int statusId) throws RepositoryException;

}
