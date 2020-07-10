package ru.geakbrains.level1.chat.server;

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
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            clients = new Vector<>();

            while (true) {
                socket = server.accept();
                clients.add(new ClientHandler(this, socket));
                System.out.println("Подключенных клиентов: "+clients.size());
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
        }
    }

    public void deleteClient(Socket socket) {
        for (int i=0;i<clients.size();i++){
            if(clients.get(i).getSocket() == socket){
                System.out.println("Отключился клиент: "+clients.get(i));
                clients.remove(i);
            }
        }
        System.out.println("Подключенные клиенты: "+clients.size());
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
}

