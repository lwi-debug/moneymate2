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
            // je reconstruit le portefeuille a partir du csv
            List<Portefeuille1> portefeuilles = CSVPortefeuilleManager.chargerPortefeuilles(email);
            utilisateur.setPortefeuilles(portefeuilles);
            // Transition
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/moneymate2/Dashboard.fxml"));
            Parent dashboardRoot = loader.load();
            // passe l'entité utilisateur en cours
            DashboardController dashboardController = loader.getController();
            dashboardController.setUtilisateurConnecte(utilisateur);
            //update
            dashboardController.updateData2();
            // Transition
            Scene scene = loginButton.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(dashboardRoot));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du tableau de bord.");
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
// cette alternative est utilisé dans le boutton login lors de la reconstitution du portefeuille a partir des info du csv. permet la supression de la les ligne qui nous intéresse . cette methode fonctionne seulement avec le request api rate de geko coin nous appelons trop souvent la methode getcryptovalue dans apicall et donc nous obtenont lerreur 429. "

/*public void onLoginButtonClick(ActionEvent event) {
    String email = emailTextField.getText();
    String motDePasse = motDePasseTextField.getText();

    Utilisateur1 utilisateur = gestionUtilisateur.connexion(email, motDePasse);
    if (utilisateur == null) {
        System.out.println("Erreur de connexion pour : " + email);
        return;
    }

    System.out.println("Connexion réussie pour : " + email);
    try {
        if (verifierPortefeuilleExistant(email)) {
            List<Portefeuille1> portefeuilles = CSVPortefeuilleManager.chargerPortefeuilles(email);
            utilisateur.setPortefeuilles(portefeuilles);

            CSVPortefeuilleManager.supprimerPortefeuille(email);

            for (Portefeuille1 portefeuille : portefeuilles) {
                portefeuille.calculerValeurTotalePortefeuille();
                CSVPortefeuilleManager.sauvegarderPortefeuilles(Arrays.asList(portefeuille), email);
            }
        }

        // Transition
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/moneymate2/Dashboard.fxml"));
        Parent dashboardRoot = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.setUtilisateurConnecte(utilisateur);
        dashboardController.updateData2();

        Scene scene = loginButton.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.setScene(new Scene(dashboardRoot));
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erreur lors du chargement du tableau de bord.");
    }
}
   /*private boolean verifierPortefeuilleExistant(String email) {
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

    private Portefeuille1 reconstruirePortefeuille(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(email)) {
                    return convertirEnPortefeuille(line); // Utilisez la méthode existante pour convertir la ligne CSV en Portefeuille1
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
