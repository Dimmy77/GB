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

    public static String[] getBlackList(String nick){
        String sql = String.format("Select id from Blacklist where ");
        try{
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                System.out.println(rs);
                //return rs.toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addToBlackList(String fromNick, String toNick){
        String sqlFromNick = String.format("Select id from Users where login = '%s'", fromNick);
        String sqlToNick = String.format("Select id from Users where login = '%s'", toNick);

        try {
            int indexToNick = statement.executeQuery(sqlToNick).getInt(1);
            int indexFromNick = statement.executeQuery(sqlFromNick).getInt(1);
            String sql = String.format("INSERT INTO Blacklist (Nick_ID, BlackNick_ID) VALUES (%i, %i)", indexFromNick, indexToNick);
            return statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeFromBlackList(String fromNick, String toNick){
        String sqlFromNick = String.format("Select id from Users where login = '%s'", fromNick);
        String sqlToNick = String.format("Select id from Users where login = '%s'", toNick);

        try {
            int indexToNick = statement.executeQuery(sqlToNick).getInt(1);
            int indexFromNick = statement.executeQuery(sqlFromNick).getInt(1);
            String sql = String.format("DELETE FROM Blacklist WHERE NickID=%i and BlackNick_ID=%i", indexFromNick, indexToNick);
            return statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static void disconnect(){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
