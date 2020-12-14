package ru.geakbrains.level2.lesson3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    private ServerSocket socket;
    private static String FILE = "stud.ser";
    private File file;


    FileServer(int port){
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){
        while (true){
            try {
                System.out.println("Server is run");
                Socket cSocket = socket.accept();
                file = new File(FILE);
                if (file.exists()){
                    System.out.println("Take new Student");
                    takeObject();
                }else{
                    System.out.println("File dont' exist");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeObject() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
        Student s2 = null;
        try {
            s2 = (Student) ois.readObject();
            ois.close();
            s2.info();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ois.close();
        }

    }

    public static void main(String[] args){
        FileServer fileServer = new FileServer(1999);
        fileServer.start();

    }
}
