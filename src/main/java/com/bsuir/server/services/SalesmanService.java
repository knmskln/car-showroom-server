package com.bsuir.server.services;

import com.bsuir.server.services.exception.ServiceException;

public interface SalesmanService {
    void changeOrderStatus(int orderId, int statusId, int userId) throws ServiceException;
}
