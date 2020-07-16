package ru.geakbrains.level1.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nick;
    private List<String> blacklist;

    public ClientHandler(Server server, Socket socket) throws IOException {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blacklist = new ArrayList<>();


            new Thread(() -> {
                try {
                    while (true){
                        String str =in.readUTF();

                        if(str.startsWith("/auth")) {
                            String[] tokens = str.split(" ");
                            String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                            if(newNick != null && !server.hasClient(newNick)){
                                sendMsg("/authok");
                                nick = newNick;
                                server.subscribe(ClientHandler.this);
                                break;
                            }else if(server.hasClient(newNick)) {
                                sendMsg("Клиент уже подключен");
                            }
                            else{
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }

                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }

                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ", 3);
                                sendMsg("to " + tokens[1] + ": " + tokens[2]);
                                server.personalMsg(tokens[2], ClientHandler.this, tokens[1]);
                            }

                            if(str.startsWith("/blacklist_add")){
                                String[] tokens = str.split(" ", 2);
                                if (!server.hasClient(tokens[1])) {
                                    //blacklist.add(tokens[1]);
                                    AuthService.addToBlackList(nick, tokens[1]);
                                    server.broadcastClientList();
                                    sendMsg("Вы добавили пользователя " + tokens[1] + "в черный список");
                                }
                                else {
                                    sendMsg("Пользователя с ником "+tokens[1]+"не существует в чате");
                                }
                            }

                            if(str.startsWith("/blacklist_remove")){
                                String[] tokens = str.split(" ", 2);
                                if (!server.hasClient(tokens[1])) {
                                    //blacklist.remove(tokens[1]);
                                    AuthService.removeFromBlackList(nick, tokens[1]);
                                    server.broadcastClientList();
                                    sendMsg("Вы удалили пользователя " + tokens[1] + "из черного списка");
                                }
                                else {
                                    sendMsg("Пользователя с ником "+tokens[1]+"не существует в чате");
                                }
                            }

                            if(str.startsWith("/clientlist")){

                            }
                        }else{
                            server.broadcastMsg(ClientHandler.this,nick + ": " + str);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(ClientHandler.this);
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick(){return nick;}

    public boolean checkBlaсkList(String nick){
        return blacklist.contains(nick);
    }

    public Socket getSocket(){
        return socket;
    }
}
