package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField motDePasseTextField;

    @FXML
    private Button loginButton;
    @FXML
    public Label inscription;

    private GestionUtilisateur gestionUtilisateur;

    public LoginController() {
        gestionUtilisateur = new GestionUtilisateur();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String email = emailTextField.getText();
        String motDePasse = motDePasseTextField.getText();

        Utilisateur1 utilisateur = gestionUtilisateur.connexion(email, motDePasse);

        if (utilisateur != null) {
            System.out.println("Connexion réussie pour : " + email);
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/moneymate2/Dashboard.fxml"));
                Parent signUpRoot = loader.load();
                Scene scene = loginButton.getScene();
                Stage stage = (Stage) scene.getWindow();
                stage.setScene(new Scene(signUpRoot));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur de connexion pour : " + email);
        }
    }
    @FXML
    void  AfficherInscription(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/moneymate2/SignUp.fxml"));
            Parent signUpRoot = loader.load();
            Scene scene = inscription.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(signUpRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
