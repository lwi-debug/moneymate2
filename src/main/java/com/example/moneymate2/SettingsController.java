package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private Label mylabel;
    @FXML
    private ChoiceBox<String> myChoicebox;
    private String[] monnaie = {"eur", "usd"};

    public Button deco;

    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    @FXML
    public Button dash;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoicebox.getItems().addAll(monnaie);
        myChoicebox.setOnAction(this::getmonnaie);

    }
    public void setGestionUtilisateur(GestionUtilisateur gestionUtilisateur) {
    }
    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
        this.gestionUtilisateur = new GestionUtilisateur();
    }

    public void getmonnaie(ActionEvent event) {
        String selectedCurrency = myChoicebox.getValue();
        mylabel.setText(selectedCurrency);
        if (selectedCurrency != null) {

            ApiCaller.setCurrency(selectedCurrency);
            CreationPortefeuilleController.setCurrency(selectedCurrency);
            PortfolioDetailsController.setCurrency(selectedCurrency);
        }
    }

    //Transition
    @FXML
    void dashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Dashboard.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = dash.getScene();

            DashboardController setController = loader.getController();
            setController.setUtilisateurConnecte(this.utilisateurConnecte);
            setController.setGestionUtilisateur(this.gestionUtilisateur);

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


