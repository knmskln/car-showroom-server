package com.bsuir.server.command;

import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.util.cooperation.ServerResponse;

public interface Command {

    ServerResponse execute() throws CommandException;
}