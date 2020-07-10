package ru.geakbrains.level1.console_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ConsoleChat {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }
}

class Server{
    private Vector<Socket> clients;
    ServerSocket server;
    Socket socket;

    Server() throws IOException, InterruptedException {
        server = new ServerSocket(8901);
        socket = null;
        clients = new Vector<Socket>();

        Thread tserver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Server started.");
                    while (true) {
                        socket = server.accept();
                        clients.add(socket);
                        System.out.println("Number of active clients is: " + clients.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tserver.setDaemon(true);

        Thread tclient = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Servers client is running");
                try {
                    new sClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                    clients.add(socket);
            }
        });
        tclient.setDaemon(true);

        Thread thend = new Thread(new Runnable() {
            @Override
            public void run() {
                DataInputStream in = null;

                while (true) {
                    String str = null;
                    try {
                        for (int i=0;i<clients.size();i++) {
                            try {
                                in = new DataInputStream(clients.get(i).getInputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            str = in.readUTF();
                            if( i==0 && str.equals("/end")){
                                broadcastMsg("/endserver");
                                break;
                            }
                            if(str.equals("/end")){
                                deleteClient(clients.get(i));
                            }
                            broadcastMsg(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tserver.start();
        Thread.sleep(1500);
        tclient.start();
        thend.start();


        try{
            tserver.join();
            tclient.join();
            thend.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        synchronized (tclient){
//            tclient.join();
//        }
//
//        synchronized (tserver){
//            tserver.join();
//        }
//
//        synchronized (thend){
//            thend.join();
//        }

    }

    public void deleteClient(Socket socket) {
        for (int i=0;i<clients.size();i++){
            if(clients.get(i) == socket){
                System.out.println("Отключился клиент: "+clients.get(i));
                clients.remove(i);
            }
        }
        System.out.println("Подключенные клиенты: "+clients.size());
    }

    public void broadcastMsg(String msg) throws IOException {
        DataOutputStream out;
        for (int i=1;i < clients.size();i++){
            out = new DataOutputStream(clients.get(i).getOutputStream());
            out.writeUTF(msg);
        }
    }
}



class StartClient {
    public static void main(String[] arg) throws IOException, InterruptedException {
        new sClient();
    }
}

class sClient{
    final String IP_ADRESS = "localhost";
    static int numberClient = 0;
    int thisClient;
    final int PORT = 8901;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Scanner consoleIn;
    PrintWriter consoleOut;
    Server server;


    sClient() throws IOException, InterruptedException {
        numberClient++;
        thisClient=numberClient;
        socket= new Socket(IP_ADRESS, PORT);
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            consoleIn = new Scanner(System.in);
            consoleOut = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String str = null;
                    try {
                        str = in.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Client "+thisClient+": "+str+"\n");
                    if (str.equalsIgnoreCase("/endserver")) {
                        consoleOut.println("Server off");
                        break;
                    }

                }
            }
        });
        thread.start();

        Thread thwrite = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.print("Введите сообщение: ");
                    String str = consoleIn.nextLine();
                    sendMsg(str);
                    System.out.println("Сообщение отправлено!");
                }

            }
        });
        thwrite.setDaemon(true);
        thwrite.start();

        synchronized (thread) {
            thread.join();
        }

        synchronized (thwrite){
            thwrite.join();
        }
    }

    public Socket getSocket(){
        return socket;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}