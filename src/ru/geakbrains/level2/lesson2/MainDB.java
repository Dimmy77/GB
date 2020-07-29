package ru.geakbrains.level2.lesson2;

import ru.geakbrains.collection.Main;

import java.io.*;
import java.sql.*;

public class MainDB {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public static void main(String[] args) {
        MainDB mainDB = new MainDB();
        mainDB.deleteTable("test");
        mainDB.createTable("test");
        System.out.println("Кол-во добавленных записей: "+mainDB.addRecord("test",new String[]{"Bob3", "Bob4", "Bob1"}, new int[]{30, 40, 10}));
        try {

            ResultSet rs = mainDB.readScore("test", "Bob3");
            while (rs.next()) {
                System.out.println("У пользователя Bob3 - " + rs.getInt("score") + " очков.");
            }
        } catch (SQLException throwables) {
           System.out.println("Пользователя Bob3 не существует");
           throwables.printStackTrace();
        }
//        mainDB.updateScore("test", 2, "Bob3",100);

        mainDB.deleteRecord("test","Bob3");

        File file = new File("C:/Java/GB/out/production/GB/ru/geakbrains/level2/lesson2/test.txt");
        FileReader fr;
        BufferedReader reader = null;
        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = reader.readLine();
            while (line!=null) {
                String[] tokens= line.split(" ");
                mainDB.updateScore("test", Integer.parseInt(tokens[0]),tokens[1], Integer.parseInt(tokens[2]));
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mainDB.disconnect();
    }

    MainDB(){
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            ResultSet rs = statement.executeQuery("SELECT id, name FROM student where id > 1");
            ResultSetMetaData rsmd = rs.getMetaData();
//            for (int i = 0; i < rsmd.getColumnCount(); i++) {
//                System.out.println((rsmd.getCatalogName(i) + " " + rsmd.getColumnType(i)+ " " + rsmd.getTableName(i)));
//            }

//            while (rs.next()){
//                System.out.println(rs.getString("name"));
//            }

//            int res = statement.executeUpdate("UPDATE student SET score = 20 WHERE id = 10");
//            System.out.println("Количество записей которые изменилось: "+ res);
//
//            boolean resb = statement.execute("DROP TABLE IF EXISTS student");
//            System.out.println("Результат выполнения операции: "+ resb);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:c:/Java/GB/DB/DB_Level3.db");
        statement = connection.createStatement();
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean updateScore(String tableName, int id, String name, int score){
        //String sql1 = String.format("UPDATE %s SET score = %i WHERE id = %i", tableName, score, id);
        String sql2 = String.format("UPDATE %s SET name = '%s'", tableName, name, id);
        try {
            return statement.execute(sql2);

            //            ResultSet rs =  statement.executeQuery(sql);
//            if (rs != null) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean createTable(String tableName){
        String sql = String.format("CREATE TABLE %s ("+
                " id    INTEGER PRIMARY KEY ASC ON CONFLICT ROLLBACK AUTOINCREMENT," +
                " name  TEXT," +
                " score INTEGER)", tableName);
        try {
            return statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int addRecord(String tableName, String[] name, int[] score){
        if(name.length != score.length){
            System.out.println("Количество добавляемых столбцов должно быть одинаковым");
            return 0;
        }
//       Savepoint svp = null;

        try {
//            svp = connection.setSavepoint();
//            for (int i = 0; i < name.length; i+=100) {
//                connection.setAutoCommit(false);
//                for (int j = 0; j < 100; i++) {
//                    String sql = String.format("INSERT INTO student (name, score) VALUES (%s, %i)", name[i+j], score[i+j]);
//                    statement.addBatch(sql);
//                }
//                statement.executeBatch();
//                connection.setAutoCommit(true);
            String sql = String.format("INSERT INTO %s (name, score) VALUES (?,?);", tableName);
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < name.length; i++) {
                preparedStatement.setString(1, name[i]);
                preparedStatement.setInt(2, score[i]);
                preparedStatement.addBatch();
            }
//            connection.commit();
            int[] a =  preparedStatement.executeBatch();
            return a.length;
        } catch (SQLException throwables) {
//            try {
//                connection.rollback(svp);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            throwables.printStackTrace();
        }
        return 0;
    }

    public static ResultSet readScore(String tableName, String userName) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE name = '%s'", tableName,userName);
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            System.out.println("Такого пользователя не существует.");
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean deleteRecord(String tableName, String userName){
        try {
            String sql = String.format("DELETE FROM %s WHERE name = '%s'",tableName, userName);
            statement.execute(sql);
            return true;
        } catch (SQLException throwables) {
            System.out.println("Такого пользователя не существует.");
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteTable(String tableName){
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        try {
            return statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
