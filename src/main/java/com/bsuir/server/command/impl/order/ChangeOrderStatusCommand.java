package com.bsuir.server.command.impl.order;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.SalesmanService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.SalesmanServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class ChangeOrderStatusCommand implements Command {
    private SalesmanService service;
    private ClientRequest request;
    private ServerResponse response;

    public ChangeOrderStatusCommand(ClientRequest request, ServerResponse response) {
        this.service = SalesmanServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int orderId = (int) data.get("orderId");
        int statusId = (int) data.get("statusId");
        int sellerId = (int) data.get("userId");
        try {
            service.changeOrderStatus(orderId, statusId, sellerId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
