package com.bsuir.server.command.impl.car;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.Car;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class GetCarByIdCommand implements Command {
    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetCarByIdCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> requestData = request.getData();
        int carId = (int) requestData.get("carId");
        try {
            Car car = service.getCarById(carId);
            Map<String, Object> data = new HashMap<>();
            data.put("car", car);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
