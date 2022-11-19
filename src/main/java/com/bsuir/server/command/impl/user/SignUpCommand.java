package com.bsuir.server.command.impl.user;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class SignUpCommand implements Command {
    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignUpCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String login = (String) data.get("login");
        String name = (String) data.get("name");
        String surname = (String) data.get("surname");
        String email = (String) data.get("email");
        String password = (String) data.get("password");
        try {
            service.signUp(login, name, surname, email, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
