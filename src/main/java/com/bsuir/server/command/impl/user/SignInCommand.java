package com.bsuir.server.command.impl.user;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.User;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class SignInCommand implements Command {
    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignInCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String login = (String) data.get("login");
        String password = (String) data.get("password");
        try {
            User user = service.signIn(login, password);
//            Basket basket = service.getBasket(user.getUserId());
//            basket.getWorkers().forEach(product -> System.out.println(product.getProductName()));

            Map<String, Object> dataSend = new HashMap<>();
            dataSend.put("user", user);

            response.setData(dataSend);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
