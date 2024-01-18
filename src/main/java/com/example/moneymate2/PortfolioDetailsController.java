package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import static java.lang.String.format;

public class PortfolioDetailsController {
    @FXML
    public JFXButton setting;
    @FXML
    public JFXButton Dash;
    @FXML
    public JFXButton evolution;
    @FXML
    private Label liquidites;

    @FXML
    private Label Crypto;

    @FXML
    private Label Action;

    @FXML
    private Label pourcentageliquidite;

    @FXML
    private Label pourcentageCrypto;

    @FXML
    private Label pourcentageAction;

    @FXML
    private Label ValeurTotale;






    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;

    public PortfolioDetailsController() {
        gestionUtilisateur = new GestionUtilisateur();
    }

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
        this.gestionUtilisateur = new GestionUtilisateur(); // Initialisation de gestionUtilisateur
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
    void afficherevolution(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioEvolution.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = evolution.getScene();

            PortfolioEvolutionController evolController = loader.getController();
            evolController.setUtilisateurConnecte(this.utilisateurConnecte);
            evolController.setGestionUtilisateur(this.gestionUtilisateur);

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData(){

        this.ValeurTotale.setText("$ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotale()));
        this.liquidites.setText("$ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getLiquidites().get(0).getMontant()));
        this.Crypto.setText("$ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleCryptos()));
        this.Action.setText("$ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleAction()));
        this.pourcentageliquidite.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurLiquidites()));
        this.pourcentageCrypto.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurCryptos()));
        this.pourcentageAction.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurActions()));


    }
    public void setGestionUtilisateur(GestionUtilisateur gestionUtilisateur) {
    }
}