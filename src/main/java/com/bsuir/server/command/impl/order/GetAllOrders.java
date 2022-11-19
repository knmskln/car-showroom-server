package com.bsuir.server.command.impl.order;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.Car;
import com.bsuir.server.entities.Order;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllOrders implements Command {
    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllOrders(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Order> orders = service.getAllOrders();
            Map<String, Object> data = new HashMap<>();
            data.put("orders", orders);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
