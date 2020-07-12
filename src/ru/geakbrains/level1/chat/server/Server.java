package ru.geakbrains.level1.chat.server;

import ru.geakbrains.level1.chat.client.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            clients = new Vector<>();

            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }


    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public boolean hasClient(String nick){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)) return true;
        }
        return false;
    }

    public void personalMsg(String msg, String toNick){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(toNick)) o.sendMsg(msg);
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        System.out.println("Подключенных клиентов: "+clients.size());
    }

    public void unsubscribe(ClientHandler clientHandler){
        System.out.println("Отключился клиент: "+clientHandler.getNick());
        clients.remove(clientHandler);
        System.out.println("Подключенные клиенты: "+clients.size());
    }
}

