package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VendreController {
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestion) {
        this.gestionUtilisateur = gestion;
    }
    @FXML
    public Button dash;
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
