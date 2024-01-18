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

    @FXML
    private Label nomCrypto;

    @FXML
    private Label quantitéCrypto;

    @FXML
    private Label nomAction;

    @FXML
    private Label quantitéAction;




    private static String currency = "usd";//par default
    public static void setCurrency(String newCurrency) {
        currency = newCurrency;}

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

        if(currency=="eur") {
            this.ValeurTotale.setText("€ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV()));
            this.liquidites.setText("€ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getLiquidites().get(0).getMontant()));
            this.Crypto.setText("€ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleCryptosFromCSV()));
            this.Action.setText("€ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleActionFromCSV()));

        }
        else{

        this.ValeurTotale.setText("$ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV()));
        this.liquidites.setText("$ "+ format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getLiquidites().get(0).getMontant()));
        this.Crypto.setText("$ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleCryptosFromCSV()));
        this.Action.setText("$ " + format("%.2f",this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleActionFromCSV()));

        }
        this.pourcentageliquidite.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurLiquidites()));
        this.pourcentageCrypto.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurCryptosFromCSV()));
        this.pourcentageAction.setText(format("%.2f%%", this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurActionFromCSV()));
        this.nomCrypto.setText(this.utilisateurConnecte.getPortefeuilles().get(0).getCrypto1().getSymbole());
        this.quantitéCrypto.setText(String.format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getCrypto1().getQuantite()));
        this.nomAction.setText(this.utilisateurConnecte.getPortefeuilles().get(0).getAction1().getSymbole());
        this.quantitéAction.setText(String.format("%.2f", this.utilisateurConnecte.getPortefeuilles().get(0).getAction1().getQuantite()));



    }
    public void setGestionUtilisateur(GestionUtilisateur gestionUtilisateur) {
    }
}