package com.example.moneymate2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField motDePasseTextField;

    @FXML
    private Button loginButton;

    private GestionUtilisateur gestionUtilisateur;

    public LoginController() {
        gestionUtilisateur = new GestionUtilisateur();
    }

    @FXML
    protected void onLoginButtonClick() {
        String email = emailTextField.getText();
        String motDePasse = motDePasseTextField.getText();

        Utilisateur1 utilisateur = gestionUtilisateur.connexion(email, motDePasse);

        if (utilisateur != null) {
            System.out.println("Connexion réussie pour : " + email);
            // Ici, tu peux continuer vers la page suivante ou effectuer d'autres actions
        } else {
            System.out.println("Erreur de connexion pour : " + email);
        }
    }
}
