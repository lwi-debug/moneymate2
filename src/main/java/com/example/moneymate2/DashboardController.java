package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private JFXButton Portefeuillecreation;
    @FXML
    void affichercreaportefeuil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/CreationP.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = Portefeuillecreation.getScene();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
