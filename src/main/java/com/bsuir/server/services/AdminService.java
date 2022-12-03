package com.bsuir.server.services;

import com.bsuir.server.entities.Car;
import com.bsuir.server.entities.Order;
import com.bsuir.server.entities.User;
import com.bsuir.server.services.exception.ServiceException;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers() throws ServiceException;
    void changeBanStatus(int userId) throws ServiceException;
    Car getCarById(int id) throws ServiceException;
    void editCar(int carId, String mark,
                    String model, Integer year,
                    String color, Integer price) throws ServiceException;
    List<Order> getOrdersForStatistics() throws ServiceException;
   int  getCountApproved(int sellerId) throws ServiceException;
}
