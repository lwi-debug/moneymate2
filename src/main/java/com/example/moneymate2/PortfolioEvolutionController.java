package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PortfolioEvolutionController {
    @FXML
    private JFXButton setting;
    @FXML
    private JFXButton PorDet;
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    private static String currency = "usd";//par default

    @FXML
    public JFXButton Dash;
    @FXML
    private Label liquidites;
    @FXML
    private Label time;
    @FXML
    private Label ValeurTotale;

    @FXML
    private Label identifiant;

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestion) {
        this.gestionUtilisateur = gestion;
    }


    public static void setCurrency(String newCurrency) {
        currency = newCurrency;}


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
            dashController.updateData2();


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
    public void Date(){
        SimpleDateFormat sdf =new SimpleDateFormat("dd MMMM yyyy");
        String datenow =sdf.format(new Date());
        time.setText(datenow);

    }
    @FXML
    public void initialize() {
        Date();
    }
    public void updateData4(){
        String symboleMonnaie = "eur".equals(currency) ? "€" : "$";

        double valeurTotale;
        String identifiant;

        if (this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant() == null || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant().isEmpty()) {
            valeurTotale = 0;
            identifiant = "N/A";
        } else {
            valeurTotale = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());

            identifiant = this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant();
        }

        this.ValeurTotale.setText(symboleMonnaie + " " + (valeurTotale == 0 ? "0.00" : String.format("%.2f", valeurTotale)));
        this.identifiant.setText(identifiant);
    }
    public void updateData5(){
        String symboleMonnaie = "eur".equals(currency) ? "€" : "$";

        double valeurTotale;
        String identifiant;

        if (this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant() == null || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant().isEmpty()) {
            valeurTotale = 0;
            identifiant = "N/A";
        } else {
            valeurTotale = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());

            identifiant = this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant();
        }

        this.ValeurTotale.setText(symboleMonnaie + " " + (valeurTotale == 0 ? "0.00" : String.format("%.2f", valeurTotale)));
        this.identifiant.setText(identifiant);

        if (this.utilisateurConnecte.getPortefeuilles().size() > 1) {
            double valeurTotale2 = this.utilisateurConnecte.getPortefeuilles().get(1).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(1).getIdentifiant());
            String identifiant2 = this.utilisateurConnecte.getPortefeuilles().get(1).getIdentifiant();
            this.ValeurTotale.setText(symboleMonnaie + " " + String.format("%.2f", valeurTotale2));
            this.identifiant.setText(identifiant2);
        } else {
            // Gérez le cas où il n'y a pas de second portefeuille
            this.ValeurTotale.setText(symboleMonnaie + " 0.00");
            this.identifiant.setText("N/A");
        }
    }
}



