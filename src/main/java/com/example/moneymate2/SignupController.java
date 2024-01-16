package com.example.moneymate2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SignupController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField motDePasseTextField;

    @FXML
    private Button signupButton;

    private GestionUtilisateur gestionUtilisateur;

    public SignupController() {
        gestionUtilisateur = new GestionUtilisateur();
    }

    @FXML
    protected void onSignupButtonClick() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String email = emailTextField.getText();
        String motDePasse = motDePasseTextField.getText();

        boolean compteCree = gestionUtilisateur.creerCompte(nom, prenom, email, motDePasse);

        if (compteCree) {
            System.out.println("Compte créé avec succès pour : " + email);
        } else {
            System.out.println("Erreur : Impossible de créer le compte pour : " + email);
        }
    }
}
