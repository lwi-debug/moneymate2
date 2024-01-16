package com.example.moneymate2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreationPortefeuilleController {
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


    @FXML
    protected void onCreerPortefeuilleButtonClick() {

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
        afficherContenuPortefeuille();
    }
    private void afficherContenuPortefeuille() {
        System.out.println("Contenu du portefeuille créé :");
        System.out.println("Liquidités : " + montantLiquiditeTextField.getText() + " EUR");
        System.out.println("Crypto : " + nomCryptoTextField.getText() + ", Quantité : " + quantiteCryptoTextField.getText());
        System.out.println("Actions : " + nomActionTextField.getText() + ", Quantité : " + quantiteActionTextField.getText());
    }
}
