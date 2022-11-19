package com.bsuir.server.util;

import com.bsuir.server.command.Command;
import com.bsuir.server.command.exception.CommandException;
import com.bsuir.server.command.factory.CommandFactory;
import com.bsuir.server.main.Runner;
import com.bsuir.server.util.cooperation.ClientRequest;
import com.bsuir.server.util.cooperation.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean continueRunning = true;
        Runner.incrementConnectionsNumber();
        Runner.incrementUsersOnline();
        while (continueRunning) {
            try {
                ClientRequest request = getData();
                String action = request.getCommandName();
                CommandFactory factory = CommandFactory.getInstance();
                Command command = factory.createCommand(action, request, new ServerResponse());
                try {
                    ServerResponse response = command.execute();
                    sendData(response);
                } catch (CommandException e) {
                    String[] messages = e.getMessage().split(": ");
                    sendData(new ServerResponse(null, true, messages[messages.length - 1]));
                }
            } catch (SocketException e) {
                continueRunning = false;
                Runner.decrementUsersOnline();
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendData(ServerResponse response) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String outputJson = mapper.writeValueAsString(response);
        outputStream.writeObject(outputJson);
    }

    private ClientRequest getData() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        String inputJson = (String) inputStream.readObject();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputJson, ClientRequest.class);
    }
}