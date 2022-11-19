package com.bsuir.server.services;

import com.bsuir.server.entities.Car;
import com.bsuir.server.entities.Order;
import com.bsuir.server.entities.User;
import com.bsuir.server.services.exception.ServiceException;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    void signUp(String login, String name,
                String surname, String email,
                String password) throws ServiceException;

    User getUser(int userId) throws ServiceException;
    User getUserByEmail(String email) throws ServiceException;
    void restorePassword(int userId, String password) throws ServiceException;
    void changeEmail(int userId, String newEmail) throws ServiceException;

    void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException;
    List<Car> getAllCars() throws ServiceException;
    void orderCar(int carId, int userId, int statusId) throws ServiceException;

    List<Order> getAllOrders() throws ServiceException;

    List<Order> getOrderByUserId(int userId) throws ServiceException;

    List<Order> getOrderByStatusId(int statusId) throws ServiceException;
}
