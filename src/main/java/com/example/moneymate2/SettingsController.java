package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController  implements Initializable {
    @FXML
    private Label mylabel;
    @FXML
    private ChoiceBox<String> myChoicebox;
    private String[]monnaie={"Euro","USD"};



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoicebox.getItems().addAll(monnaie);
        myChoicebox.setOnAction(this::getmonnaie);

    }
    public void getmonnaie(ActionEvent event){
        String mamonnaie=myChoicebox.getValue();
        mylabel.setText(mamonnaie);
    }
}

