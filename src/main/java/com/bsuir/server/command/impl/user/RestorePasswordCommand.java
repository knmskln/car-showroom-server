package com.bsuir.server.command.impl.user;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.entities.User;
import com.bsuir.server.services.UserService;
import com.bsuir.server.services.exception.ServiceException;
import com.bsuir.server.services.impl.UserServiceImpl;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;
import com.bsuir.server.util.email.EmailSender;
import com.bsuir.server.util.hasher.PasswordHashKeeper;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class RestorePasswordCommand implements Command {
    private final UserService service;
    private final ClientRequest request;
    private final ServerResponse response;
    private final EmailSender sender = EmailSender.getInstance();
    private final PasswordHashKeeper hashKeeper = PasswordHashKeeper.getInstance();

    public RestorePasswordCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImpl.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String email = (String) data.get("email");
        String password = RandomStringUtils.random(12, true, true);
        sender.sendEmail(email, "Восстановление пароля",
                "Здравствуйте!\n" +
                        "Ваш новый пароль для входа в систему: " + password);
        try {
            User user = service.getUserByEmail(email);
            String encoded = hashKeeper.generateHash(user.getLogin(), password);
            service.restorePassword(user.getUserId(), encoded);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
