package com.bsuir.server.command.impl.user;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class ChangePasswordCommand implements Command {
    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public ChangePasswordCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        String currentPassword = (String) data.get("currentPassword");
        String newPassword = (String) data.get("newPassword");
        try {
            service.changePassword(userId, currentPassword, newPassword);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
