package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.moneymate2.CSVPortefeuilleManager.convertirEnPortefeuille;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField motDePasseTextField;

    @FXML
    private Button loginButton;
    @FXML
    public Label inscription;


    private GestionUtilisateur gestionUtilisateur;

    public LoginController() {
        gestionUtilisateur = new GestionUtilisateur();
    }


    public void onLoginButtonClick(ActionEvent event) {
        String email = emailTextField.getText();
        String motDePasse = motDePasseTextField.getText();

        Utilisateur1 utilisateur = gestionUtilisateur.connexion(email, motDePasse);
        if (utilisateur == null) {
            System.out.println("Erreur de connexion pour : " + email);
            return;
        }

        System.out.println("Connexion réussie pour : " + email);
        try {
            // Charger et reconstruire le(s) portefeuille(s) de l'utilisateur
            List<Portefeuille1> portefeuilles = CSVPortefeuilleManager.chargerPortefeuilles(email);
            utilisateur.setPortefeuilles(portefeuilles);

            // Charger le tableau de bord (Dashboard) après la connexion réussie
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/moneymate2/Dashboard.fxml"));
            Parent dashboardRoot = loader.load();

            // Récupérer le contrôleur du tableau de bord et définir l'utilisateur connecté
            DashboardController dashboardController = loader.getController();
            dashboardController.setUtilisateurConnecte(utilisateur);

            // Mettre à jour les données du tableau de bord en fonction des portefeuilles de l'utilisateur
            dashboardController.updateData2();
            // Utilisez également updateData et updateData3 si nécessaire

            // Mettre en place la nouvelle scène
            Scene scene = loginButton.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(dashboardRoot));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du tableau de bord.");
        }
    }


    private boolean verifierPortefeuilleExistant(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
