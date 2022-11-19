package com.bsuir.server.command.impl.car;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class EditCarCommand implements Command {
    private UserService userService;
    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public EditCarCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();

        int carId = (int) data.get("carId");
        String mark = (String) data.get("mark");
        String model = (String) data.get("model");
        int year = (int) data.get("year");
        String color = (String) data.get("color");
        int price = (int) data.get("price");

        try {
            service.editCar(carId, mark, model, year, color, price);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
