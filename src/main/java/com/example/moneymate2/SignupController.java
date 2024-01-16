package com.example.moneymate2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignupController {
    @FXML

    public Label connexionLabel;

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
    @FXML
    void afficherConnexion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Login.fxml"));
            Parent logInRoot = loader.load();
            Scene scene = connexionLabel.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(logInRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
