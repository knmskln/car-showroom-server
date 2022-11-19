package com.bsuir.server.repositories;

import com.bsuir.server.entities.Order;
import com.bsuir.server.entities.User;
import com.bsuir.server.repositories.exception.RepositoryException;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface UserRepository {

    List<User> getAll() throws RepositoryException;

    User get(String login, String password) throws RepositoryException;

    User get(int userId) throws RepositoryException;

    User getByLogin(String login) throws RepositoryException;

    User getByEmail(String email) throws RepositoryException;

    void add(User user) throws RepositoryException;

    void update(User user) throws RepositoryException;


}
