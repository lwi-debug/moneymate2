package com.example.moneymate2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage)throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
