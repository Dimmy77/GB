package ru.geakbrains.level1.chat.server;

import java.io.File;
import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            String separator = File.separator;
//            String databasePath = DatabaseMetaData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//            String currentDir = databasePath.substring(0,databasePath.lastIndexOf(separator)+1)+"raw"+separator+"main.db";
//
//            System.out.println("Separator - " +separator);
//            System.out.println("DatabasePath - " +databasePath);
//            System.out.println("currentDir - " +"jdbc:sqlite:"+currentDir);
            connection = DriverManager.getConnection("jdbc:sqlite:c:/Java/GB/DB/mainDB.db");

            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println("Can't connect to DB main.db.");
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass){
        String sql = String.format("Select nickname from Users where login = '%s' and password = '%s'", login, pass);
        try {
            ResultSet rs= statement.executeQuery(sql);
            if (rs.next()){
                return rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect(){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
