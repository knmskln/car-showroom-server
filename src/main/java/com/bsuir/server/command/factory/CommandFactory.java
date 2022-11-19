package com.bsuir.server.command.factory;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.impl.car.*;
import com.bsuir.server.command.impl.order.ChangeOrderStatusCommand;
import com.bsuir.server.command.impl.order.GetAllOrders;
import com.bsuir.server.command.impl.order.GetOrderByStatusIdCommand;
import com.bsuir.server.command.impl.order.GetOrderByUserIdCommand;
import com.bsuir.server.command.impl.user.*;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;
public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    private CommandFactory() {
    }

    public Command createCommand(String name, ClientRequest request, ServerResponse response) {

        System.out.println("COMMAND received: " + name);

        switch (name) {
            case "signIn":
                return new SignInCommand(request, response);
            case "signUp":
                return new SignUpCommand(request, response);
            case "restorePassword":
                return new RestorePasswordCommand(request, response);
            case "getAllCars":
                return new GetAllCarsCommand(request, response);
            case "addCar":
                return new AddCarCommand(request, response);
            case "deleteCar":
                return new DeleteCarCommand(request, response);
            case "getUser":
                return new GetUserCommand(request, response);
            case "getOrderByUserId":
                return new GetOrderByUserIdCommand(request, response);
            case "getOrderByStatus":
                return new GetOrderByStatusIdCommand(request, response);
            case "changeEmail":
                return new ChangeEmailCommand(request, response);
            case "changePassword":
                return new ChangePasswordCommand(request, response);
            case "editCar":
                return new EditCarCommand(request, response);
            case "getCarById":
                return new GetCarByIdCommand(request, response);
            case "getAllUsers":
                return new GetAllUsersCommand(request, response);
            case "changeBanStatus":
                return new ChangeBanStatusCommand(request, response);
            case "changeOrderStatus":
                return new ChangeOrderStatusCommand(request, response);
            case "orderCar":
                return new OrderCarCommand(request, response);
            case "getAllOrders":
                return new GetAllOrders(request, response);
        }
        throw new RuntimeException("Команда не нашлась..");
    }
}