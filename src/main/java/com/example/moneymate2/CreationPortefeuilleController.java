package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
    @FXML
    private Label time;
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

        utilisateurConnecte.ajouterPortefeuille(portefeuille);
        gestionUtilisateur.sauvegarderUtilisateur(utilisateurConnecte);
        portefeuille.calculerValeurTotalePortefeuille();
        afficherContenuPortefeuille(portefeuille);
        sauvegarderPortefeuille(portefeuille);
        afficherListePortefeuilles();


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
            detailController.updateData();

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
            dashController.updateData2();

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
    public void Date(){
        SimpleDateFormat sdf =new SimpleDateFormat("dd MMMM yyyy");
        String datenow =sdf.format(new Date());
        time.setText(datenow);

    }
    @FXML
    public void initialize() {
        Date();
    }
    private void afficherListePortefeuilles() {
        if (utilisateurConnecte != null && utilisateurConnecte.getPortefeuilles() != null) {
            System.out.println("Liste des portefeuilles pour l'utilisateur " + utilisateurConnecte.getEmail() + ":");
            for (Portefeuille1 portefeuille : utilisateurConnecte.getPortefeuilles()) {
                System.out.println("Portefeuille ID: " + portefeuille.getIdentifiant());
                // Vous pouvez afficher d'autres détails ici si nécessaire
            }
        } else {
            System.out.println("Aucun utilisateur connecté ou pas de portefeuilles disponibles.");
        }
    }

}
