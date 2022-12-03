package com.bsuir.server.command.impl.statistics;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.Order;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOrdersForStatisticsCommand implements Command {
    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetOrdersForStatisticsCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Order> orders = service.getOrdersForStatistics();
            Map<String, Object> dataSend = new HashMap<>();
            dataSend.put("orders", orders);
            response.setData(dataSend);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
