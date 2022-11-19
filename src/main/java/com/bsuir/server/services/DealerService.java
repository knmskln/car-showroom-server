package com.bsuir.server.services;

import com.bsuir.server.services.exception.ServiceException;

public interface DealerService {
    void addCar(String mark, String model,
                Integer year, String color,
                Integer price) throws ServiceException;
    void deleteCar(int carId) throws ServiceException;
}
