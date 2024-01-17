package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PortfolioEvolutionController {
    @FXML
    private JFXButton setting;
    @FXML
    private JFXButton PorDet;
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;

    @FXML
    public JFXButton Dash;
    @FXML
    private Label liquidites;

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestion) {
        this.gestionUtilisateur = gestion;
    }


    @FXML
    void affichersetting(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Settings.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = setting.getScene();

            SettingsController setController = loader.getController();
            setController.setUtilisateurConnecte(this.utilisateurConnecte);
            setController.setGestionUtilisateur(this.gestionUtilisateur);

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void afficherDashboard(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Dashboard.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = Dash.getScene();

            DashboardController dashController = loader.getController();
            dashController.setUtilisateurConnecte(this.utilisateurConnecte);
            dashController.setGestionUtilisateur(this.gestionUtilisateur);


            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherportfoliodetails(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioDetails.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = PorDet.getScene();

            PortfolioDetailsController detailController = loader.getController();
            detailController.setUtilisateurConnecte(this.utilisateurConnecte);
            detailController.setGestionUtilisateur(this.gestionUtilisateur);
            detailController.updateData();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}