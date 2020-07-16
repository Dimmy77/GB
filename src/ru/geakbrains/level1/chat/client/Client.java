package ru.geakbrains.level1.chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        primaryStage.setTitle("Heroes chat");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }
}
