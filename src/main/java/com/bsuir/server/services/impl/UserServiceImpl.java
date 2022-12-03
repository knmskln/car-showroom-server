package com.bsuir.server.services.impl;

import com.bsuir.server.entities.*;
import com.bsuir.server.repositories.CarRepository;
import com.bsuir.server.repositories.OrderRepository;
import com.bsuir.server.repositories.StatusRepository;
import com.bsuir.server.repositories.UserRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.repositories.impl.CarJpaRepository;
import com.bsuir.server.repositories.impl.OrderJpaRepository;
import com.bsuir.server.repositories.impl.StatusJpaRepository;
import com.bsuir.server.repositories.impl.UserJpaRepository;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.util.email.EmailSender;
import com.bsuir.server.util.validator.UserInformationValidator;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {
    }

    private final UserRepository userRepository = UserJpaRepository.getInstance();
    private final CarRepository carRepository = CarJpaRepository.getInstance();

    private final OrderRepository orderRepository = OrderJpaRepository.getInstance();

    private final StatusRepository statusRepository = StatusJpaRepository.getInstance();

    private final UserInformationValidator userValidator = UserInformationValidator.getInstance();


    @Override
    public User signIn(String login, String password) throws ServiceException {
        if (!userValidator.validate(login)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            User user = userRepository.get(login, password);
            if (user != null) {
                if (user.isBanned()) {
                    throw new ServiceException("Пользователь заблокирован!");
                }
                return user;
            } else {
                throw new ServiceException("Введен неверный логин и/или пароль. Пожалуйста, повторите ввод.");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signUp(String login, String name, String surname,
                       String email, String password) throws ServiceException {
        if (!userValidator.validate(login, name, surname, email)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            User existingUserByUsername = userRepository.getByLogin(login);
            User existingUserByEmail = userRepository.getByEmail(email);
            if (existingUserByUsername == null && existingUserByEmail == null) {
                User user = new User(login, name, surname,
                        new Role(2, "user"),
                        email, false, password);
                userRepository.add(user);
                userRepository.getByLogin(login);

                EmailSender.getInstance().sendEmail(
                        email,
                        "Автосалон",
                        name + ", спасибо за регистрацию!"
                );
            } else {
                throw new ServiceException("Пользователь с таким логином и/или адресом эл. почты " +
                        "уже зарегистрирован! Пожалуйста, повторите ввод.");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public User getUser(int userId) throws ServiceException {
        try {
            return userRepository.get(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            return userRepository.getByEmail(email);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void restorePassword(int userId, String password) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            user.setPassword(password);
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void changeEmail(int userId, String newEmail) throws ServiceException {
        if (!userValidator.validateEmail(newEmail)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            User existingUser = userRepository.getByEmail(newEmail);
            if (existingUser == null) {
                User user = userRepository.get(userId);
                user.setEmail(newEmail);
                userRepository.update(user);
            } else {
                throw new ServiceException("Пользователь с таким адресом эл. почты уже зарегистрирован!" +
                        "Пожалуйста, повторите ввод.");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            if (currentPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
                userRepository.update(user);
            } else {
                throw new ServiceException("Неверный пароль! Пожалуйста, повторите ввод.");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getAllCars() throws ServiceException {
        try {
            return carRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void orderCar(int carId,
                        int userId,
                        int statusId) throws ServiceException {
        try {
            Car car = carRepository.get(carId);
            User user = userRepository.get(userId);
            Status status = statusRepository.get(statusId);
            Order order = new Order();
            order.setCarId(car);
            order.setUserId(user);
            order.setOrderStatus(status);
            order.setSellerId(user);
            orderRepository.add(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return orderRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Order> getOrderByUserId(int userId) throws ServiceException {
        try {
            return orderRepository.getOrderByUserId(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrderByStatusId(int statusId) throws ServiceException {
        try {
            return orderRepository.getOrderByStatusId(statusId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}

