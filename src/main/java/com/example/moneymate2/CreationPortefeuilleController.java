package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

public class CreationPortefeuilleController {
    @FXML
    public JFXButton Dash;
    @FXML
    public JFXButton evolution;
    @FXML
    private JFXButton setting;
    @FXML
    private JFXButton PorDet;
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    @FXML
    private TextField montantLiquiditeTextField;
    @FXML
    private TextField nomCryptoTextField;
    @FXML
    private TextField quantiteCryptoTextField;
    @FXML
    private TextField nomActionTextField;
    @FXML
    private TextField quantiteActionTextField;
    @FXML
    private Button creerPortefeuilleButton;
    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestion) {
        this.gestionUtilisateur = gestion;
    }


    public CreationPortefeuilleController() {
        // Constructeur par défaut
    }
    private static String currency = "usd";//par default
    public static void setCurrency(String newCurrency) {
        currency = newCurrency;}



    @FXML
    protected void onCreerPortefeuilleButtonClick() {

        if (utilisateurConnecte == null) {
            System.out.println("Erreur: Aucun utilisateur connecté.");
            return;
        }

        if (utilisateurConnecte == null || gestionUtilisateur == null) {
            System.out.println("Erreur: Utilisateur non connecté ou gestionnaire d'utilisateurs non initialisé.");
            return;
        }
        double montantLiquidite = Double.parseDouble(montantLiquiditeTextField.getText());
        String symboleCrypto = nomCryptoTextField.getText();
        double quantiteCrypto = Double.parseDouble(quantiteCryptoTextField.getText());
        String symboleAction = nomActionTextField.getText();
        double quantiteAction = Double.parseDouble(quantiteActionTextField.getText());

        Liquidité1 liquidite = new Liquidité1(montantLiquidite);
        Crypto1 crypto = new Crypto1(symboleCrypto, 0, quantiteCrypto); // Prix unitaire mis à 0 pour l'instant
        Action1 action = new Action1(symboleAction, 0, quantiteAction); // Prix unitaire mis à 0 pour l'instant
        Portefeuille1 portefeuille = new Portefeuille1();
        portefeuille.ajouterLiquidite(liquidite);
        portefeuille.ajouterCrypto(crypto);
        portefeuille.ajouterAction(action);

        if (!portefeuille.estVide()) {
            utilisateurConnecte.ajouterPortefeuille(portefeuille);
            gestionUtilisateur.sauvegarderUtilisateur(utilisateurConnecte);
        } else {
            System.out.println("Le portefeuille est vide.");
        }
        portefeuille.calculerValeurTotalePortefeuille();
        afficherContenuPortefeuille(portefeuille);
        sauvegarderPortefeuille(portefeuille);

    }
    private void afficherContenuPortefeuille(Portefeuille1 portefeuille) {
        double totalLiquidites = portefeuille.valeurTotaleLiquidites();
        double totalCryptos = portefeuille.valeurTotaleCryptos();
        double totalActions = portefeuille.valeurTotaleActions();
        double valeurTotalePortefeuille = portefeuille.getValeurTotalePortefeuille();

        double pourcentageLiquidites = portefeuille.pourcentageValeurLiquidites();
        double pourcentageCryptos = portefeuille.pourcentageValeurCryptos();
        double pourcentageActions = portefeuille.pourcentageValeurActions();

        System.out.println("Contenu du portefeuille créé (ID: " + portefeuille.getIdentifiant() + ") :");
        System.out.println("Total Liquidités: " + totalLiquidites + " " + currency + " (" + pourcentageLiquidites + "% du total)");
        System.out.println("Valeur totale des Cryptos: " + totalCryptos + " " + currency + " (" + pourcentageCryptos + "% du total)");
        System.out.println("Valeur totale des Actions: " + totalActions + " " + currency + " (" + pourcentageActions + "% du total)");
        System.out.println("Valeur totale du Portefeuille: " + valeurTotalePortefeuille + " " + currency);
    }

    @FXML
    void  affichersetting(MouseEvent event) {
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
    void afficherportfoliodetails(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioDetails.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = PorDet.getScene();

            PortfolioDetailsController detailController = loader.getController();
            detailController.setUtilisateurConnecte(this.utilisateurConnecte);
            detailController.setGestionUtilisateur(this.gestionUtilisateur);

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

            DashboardController dashController = loader.getController();
            dashController.setUtilisateurConnecte(this.utilisateurConnecte);
            dashController.setGestionUtilisateur(this.gestionUtilisateur);

            Scene scene = Dash.getScene();
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
    private void sauvegarderPortefeuille(Portefeuille1 portefeuille) {
        // Ajoutez la logique nécessaire pour récupérer l'email de l'utilisateur connecté
        String emailUtilisateur = utilisateurConnecte.getEmail();

        // Sauvegarder le portefeuille dans le CSV
        CSVPortefeuilleManager.sauvegarderPortefeuilles(Arrays.asList(portefeuille), emailUtilisateur);
    }

}
