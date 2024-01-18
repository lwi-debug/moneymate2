package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @FXML
    private Label Identifiant;
    @FXML
    private Label time;
    private static String currency = "usd";
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;

    public PortfolioDetailsController() {
        gestionUtilisateur = new GestionUtilisateur();
    }

    public static void setCurrency(String newCurrency) {
        currency = newCurrency;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestionUtilisateur) {
    }

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
        this.gestionUtilisateur = new GestionUtilisateur();
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
            dashController.updateData2();

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
            evolController.updateData4();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        String symboleMonnaie = "eur".equals(currency) ? "€" : "$";
        String identifiant;

        if (this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant() == null || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant().isEmpty()) {
            identifiant = "N/A";
        } else {
            identifiant = this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant();
        }
        this.Identifiant.setText(identifiant);


        // On recupere les valeurs tous en gerant le cas ou il ny a rien avec les operateurs ternaires.
        double valeurTotale = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());
        double valeurLiquidites = this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getLiquidites().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getLiquidites().get(0).getMontant();
        double valeurCryptos = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleCryptosFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());
        double valeurActions = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleActionFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());
        double pourcentageLiquidites = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurLiquidites();
        double pourcentageCryptos = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurCryptosFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());
        double pourcentageActions = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getPourcentageValeurActionFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());

        // on met a jours les label
        this.ValeurTotale.setText(symboleMonnaie + " " + String.format("%.2f", valeurTotale));
        this.liquidites.setText(symboleMonnaie + " " + String.format("%.2f", valeurLiquidites));
        this.Crypto.setText(symboleMonnaie + " " + String.format("%.2f", valeurCryptos));
        this.Action.setText(symboleMonnaie + " " + String.format("%.2f", valeurActions));
        this.pourcentageliquidite.setText(String.format("%.2f%%", pourcentageLiquidites));
        this.pourcentageCrypto.setText(String.format("%.2f%%", pourcentageCryptos));
        this.pourcentageAction.setText(String.format("%.2f%%", pourcentageActions));

        // pareil on gere les cas ou cest null et on recupere
        Crypto1 crypto = this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getCrypto1().isEmpty() ? null : this.utilisateurConnecte.getPortefeuilles().get(0).getCrypto1();
        Action1 action = this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getAction1().isEmpty() ? null : this.utilisateurConnecte.getPortefeuilles().get(0).getAction1();
        this.nomCrypto.setText(crypto != null ? crypto.getSymbole() : "N/A");
        this.quantitéCrypto.setText(crypto != null ? String.format("%.2f", crypto.getQuantite()) : "0.00");
        this.nomAction.setText(action != null ? action.getSymbole() : "N/A");
        this.quantitéAction.setText(action != null ? String.format("%.2f", action.getQuantite()) : "0.00");
    }

    public void updateData3() {
        String symboleMonnaie = "eur".equals(currency) ? "€" : "$";

        // est ce quil y a un deuxieme portefeuille ?
        if (this.utilisateurConnecte.getPortefeuilles().size() > 1) {
            Portefeuille1 deuxiemePortefeuille = this.utilisateurConnecte.getPortefeuilles().get(1);
            String identifiant = deuxiemePortefeuille.getIdentifiant();

            this.Identifiant.setText(identifiant);

            // On recupere les valeurs tous en gerant le cas ou il ny a rien avec les operateurs ternaires.
            double valeurTotale = deuxiemePortefeuille.getValeurTotaleFromCSV(identifiant);
            double valeurLiquidites = deuxiemePortefeuille.getLiquidites().isEmpty() ? 0 : deuxiemePortefeuille.getLiquidites().get(0).getMontant();
            double valeurCryptos = deuxiemePortefeuille.getValeurTotaleCryptosFromCSV(identifiant);
            double valeurActions = deuxiemePortefeuille.getValeurTotaleActionFromCSV(identifiant);
            double pourcentageLiquidites = deuxiemePortefeuille.getPourcentageValeurLiquidites();
            double pourcentageCryptos = deuxiemePortefeuille.getPourcentageValeurCryptosFromCSV(identifiant);
            double pourcentageActions = deuxiemePortefeuille.getPourcentageValeurActionFromCSV(identifiant);

            // on met a jours les label
            this.ValeurTotale.setText(symboleMonnaie + " " + String.format("%.2f", valeurTotale));
            this.liquidites.setText(symboleMonnaie + " " + String.format("%.2f", valeurLiquidites));
            this.Crypto.setText(symboleMonnaie + " " + String.format("%.2f", valeurCryptos));
            this.Action.setText(symboleMonnaie + " " + String.format("%.2f", valeurActions));
            this.pourcentageliquidite.setText(String.format("%.2f%%", pourcentageLiquidites));
            this.pourcentageCrypto.setText(String.format("%.2f%%", pourcentageCryptos));
            this.pourcentageAction.setText(String.format("%.2f%%", pourcentageActions));

            // pareil on gere les cas ou c et a sont null et on recupere
            Crypto1 crypto = deuxiemePortefeuille.getCrypto1();
            Action1 action = deuxiemePortefeuille.getAction1();
            this.nomCrypto.setText(crypto != null ? crypto.getSymbole() : "N/A");
            this.quantitéCrypto.setText(crypto != null ? String.format("%.2f", crypto.getQuantite()) : "0.00");
            this.nomAction.setText(action != null ? action.getSymbole() : "N/A");
            this.quantitéAction.setText(action != null ? String.format("%.2f", action.getQuantite()) : "0.00");
        } else {
            // si il n'y a pas de portefeuille
            this.Identifiant.setText("N/A");
        }
    }

    @FXML
    public void initialize() {
        Date();
    }


    public void Date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String datenow = sdf.format(new Date());
        time.setText(datenow);

    }
}