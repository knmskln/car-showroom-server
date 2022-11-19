package com.bsuir.server.command.impl.car;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.DealerService;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.services.impl.DealerServiceImpl;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class AddCarCommand implements Command {
    private DealerService service;
    //private UserService userService;
    private ClientRequest request;
    private ServerResponse response;

    public AddCarCommand(ClientRequest request, ServerResponse response) {
        this.service = DealerServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();

        String mark = (String) data.get("mark");
        String model = (String) data.get("model");
        Integer year = (Integer) data.get("year");
        String color = (String) data.get("color");
        Integer price = (Integer) data.get("price");

        try {
            service.addCar(mark, model, year, color, price);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
