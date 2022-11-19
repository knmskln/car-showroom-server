package com.bsuir.server.services.impl;

import com.bsuir.server.entities.Car;
import com.bsuir.server.repositories.CarRepository;
import com.bsuir.server.repositories.exception.RepositoryException;
import com.bsuir.server.repositories.impl.CarJpaRepository;
import com.bsuir.server.services.DealerService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.util.validator.CarInformationValidator;

public class DealerServiceImpl implements DealerService {
    private static final DealerServiceImpl INSTANCE = new DealerServiceImpl();

    public static DealerServiceImpl getInstance() {
        return INSTANCE;
    }

    private DealerServiceImpl() {
    }
    private final CarInformationValidator carValidator = CarInformationValidator.getInstance();
    private final CarRepository carRepository = CarJpaRepository.getInstance();
    @Override
    public void deleteCar(int carId) throws ServiceException {
        try {
            carRepository.delete(carId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void addCar(String mark, String model, Integer year, String color, Integer price) throws ServiceException {
        if (!carValidator.validate(mark, model, color,year, price)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {//
            Car car = new Car();
            car.setMark(mark);
            car.setModel(model);
            car.setYear(year);
            car.setColor(color);
            car.setPrice(price);
            carRepository.add(car);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }

    }
}
