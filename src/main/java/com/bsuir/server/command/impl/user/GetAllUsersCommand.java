package com.bsuir.server.command.impl.user;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.User;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetAllUsersCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<User> users = service.getAllUsers();
            Map<String, Object> data = new HashMap<>();
            data.put("users", users);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}