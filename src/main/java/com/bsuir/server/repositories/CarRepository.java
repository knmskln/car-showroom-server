package com.bsuir.server.repositories;

import com.bsuir.server.entities.Car;
import com.bsuir.server.repositories.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> getAll() throws RepositoryException;

    Car get(int carId) throws RepositoryException;

    void add(Car car) throws RepositoryException;

    void update(Car car) throws RepositoryException;

    void delete(int carId) throws RepositoryException;
}
