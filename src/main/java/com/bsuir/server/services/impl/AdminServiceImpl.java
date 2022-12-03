package com.bsuir.server.services.impl;
import com.bsuir.server.entities.Car;
import com.bsuir.server.entities.Order;
import com.bsuir.server.entities.User;
import com.bsuir.server.repositories.CarRepository;
import com.bsuir.server.repositories.OrderRepository;
import com.bsuir.server.repositories.UserRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.repositories.impl.*;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.util.validator.CarInformationValidator;

import java.util.List;


public class AdminServiceImpl implements AdminService {
    private static final AdminServiceImpl INSTANCE = new AdminServiceImpl();

    public static AdminServiceImpl getInstance() {
        return INSTANCE;
    }

    private AdminServiceImpl() {
    }

    private final UserRepository userRepository = UserJpaRepository.getInstance();
    private final CarRepository carRepository = CarJpaRepository.getInstance();
    private final OrderRepository orderRepository = OrderJpaRepository.getInstance();

    private final CarInformationValidator carValidator = CarInformationValidator.getInstance();


    @Override
    public Car getCarById(int id) throws ServiceException {
        try {
            return carRepository.get(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void changeBanStatus(int userId) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            user.setBanned(!user.isBanned());
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editCar(int carId,
                        String mark,
                        String model,
                        Integer year,
                        String color,
                        Integer price) throws ServiceException {
        if (!carValidator.validate(mark, model, color, year, price)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            Car car = carRepository.get(carId);
            car.setMark(mark);
            car.setModel(model);
            car.setYear(year);
            car.setColor(color);
            car.setPrice(price);
            carRepository.update(car);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Order> getOrdersForStatistics() throws ServiceException {
        try {
            return orderRepository.getOrdersForStatistics();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public int getCountApproved(int sellerId) throws ServiceException {
        try {
            return orderRepository.getCountApproved(sellerId).size();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
