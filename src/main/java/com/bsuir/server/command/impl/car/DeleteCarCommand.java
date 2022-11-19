package com.bsuir.server.command.impl.car;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.services.AdminService;
import com.bsuir.server.services.DealerService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.AdminServiceImpl;
import com.bsuir.server.services.impl.DealerServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;

import java.util.Map;

public class DeleteCarCommand implements Command {
    private DealerService service;
    private ClientRequest request;
    private ServerResponse response;

    public DeleteCarCommand(ClientRequest request, ServerResponse response) {
        this.service = DealerServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int carId = (int) data.get("carId");
        try {
            service.deleteCar(carId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
