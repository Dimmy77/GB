package ru.geakbrains.level1.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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


    public void broadcastMsg(ClientHandler fromClient, String msg) {
        for (ClientHandler o: clients) {
            if(!o.checkBlaсkList(fromClient.getNick())){
                o.sendMsg(msg);
            }
        }
    }

    public boolean hasClient(String nick){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)) return true;
        }
        return false;
    }

    public void personalMsg(String msg, ClientHandler fromClient, String toNick){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(toNick)){
                o.sendMsg("from " +fromClient.getNick() + ": " + msg);
                o.sendMsg("to " + toNick+ ": " + msg);
                return;
            }
            fromClient.sendMsg("Клиент с ником " + toNick+ " не найден в чате.");
        }

    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        System.out.println("Подключенных клиентов: "+clients.size());
        broadcastClientList();
    }

    public void broadcastClientList(){
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler o: clients) {
            sb.append(o.getNick()+" ");
        }

        for (ClientHandler o: clients) {
            o.sendMsg(sb.toString());
        }

    }

    public void unsubscribe(ClientHandler clientHandler){
        System.out.println("Отключился клиент: "+clientHandler.getNick());
        clients.remove(clientHandler);
        System.out.println("Подключенные клиенты: "+clients.size());
        broadcastClientList();
    }
}

