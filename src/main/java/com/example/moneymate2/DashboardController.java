package com.example.moneymate2;

import com.jfoenix.controls.JFXButton;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DashboardController {
    @FXML
    private JFXButton Portefeuillecreation;
    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    @FXML
    private JFXButton setting;
    @FXML
    private JFXButton PorDet;
    @FXML
    private JFXButton evolution;

    @FXML
    private ListView<String> Actu;

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
        this.gestionUtilisateur = new GestionUtilisateur(); // Initialisation de gestionUtilisateur
    }
    public void affichercreaportefeuil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/CreationP.fxml"));
            Parent settingsRoot = loader.load();

             // Assurez-vous que cette ligne est correcte
            CreationPortefeuilleController creationController = loader.getController();
            creationController.setUtilisateurConnecte(this.utilisateurConnecte);
            creationController.setGestionUtilisateur(this.gestionUtilisateur);

            Scene scene = Portefeuillecreation.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        readRss("https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/section/business/small-business/rss.xml");
    }

    @FXML void readRss(String rssUrl) {
        new Thread(() -> {
            try {
                URL feedSource = new URL(rssUrl);
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedSource));

                List<SyndEntry> entries = feed.getEntries();
                Platform.runLater(() -> {
                    for (SyndEntry entry : entries) {
                        String link = entry.getLink(); // Récupérer le lien
                        String newsItem = "Title: " + entry.getTitle() + "\nDate: " + entry.getPublishedDate() + "\nDescription: " + entry.getDescription().getValue() + "\nLink: " + link;
                        Actu.getItems().add(newsItem);
                    }
                });
            } catch (FeedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    @FXML
    void afficherportfoliodetails(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioDetails.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = PorDet.getScene();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void affichersetting(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Settings.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = setting.getScene();

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

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




