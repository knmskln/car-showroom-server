package com.bsuir.server.command.impl.statistics;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.Order;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCountApprovedCommand implements Command {
    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetCountApprovedCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int sellerId = (int) data.get("sellerId");
        try{
           int orders = service.getCountApproved(sellerId);
            Map<String, Object> dataSend = new HashMap<>();
            dataSend.put("count", orders);
            response.setData(dataSend);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
