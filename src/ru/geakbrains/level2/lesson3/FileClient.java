package ru.geakbrains.level2.lesson3;

import java.io.*;
import java.net.Socket;

public class FileClient {
    private Socket socket;
    private static String FILE = "stud.ser";
    private File file;


    FileClient(String host, int port, Student obj){
        try {
            file = new File(FILE);
            if (file.exists()){
                file.delete();
                file.createNewFile();
            }
            sendObject(obj);
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FileClient("localhost", 1999, new Student("Bob", 10));
    }

    public void sendObject(Student obj){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(FILE));
            outputStream.writeObject(obj);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
