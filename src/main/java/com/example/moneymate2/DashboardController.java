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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private JFXButton evolution1;
    @FXML
    private JFXButton evolution2;
    @FXML
    private JFXButton Portefeuille1;
    @FXML
    private JFXButton Portefeuille2;
    @FXML
    private Label time;

    @FXML
    private Label ValeurTotale;

    @FXML
    private Label id;

    @FXML
    private Label ValeurTotale2;

    @FXML
    private Label id2;
    private static String currency = "usd";//par default


    @FXML
    private ListView<String> Actu;

    public DashboardController() {
        gestionUtilisateur = new GestionUtilisateur();
    }
    public static void setCurrency(String newCurrency) {
        currency = newCurrency;}

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
        readRss("https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/international/section/business/economy/rss.xml");
        Date();
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
                        String newsItem = entry.getTitle() + "\n" + entry.getPublishedDate() + "\n" + entry.getDescription().getValue() + "\nLink: " + link;
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
    void afficherportfolio1details(MouseEvent event) {
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
    void afficherportfolio2details(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioDetails.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = PorDet.getScene();

            PortfolioDetailsController detailController = loader.getController();
            detailController.setUtilisateurConnecte(this.utilisateurConnecte);
            detailController.setGestionUtilisateur(this.gestionUtilisateur);
            detailController.updateData3();

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
    void afficherevolution(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioEvolution.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = evolution.getScene();

            PortfolioEvolutionController evolController = loader.getController();
            evolController.setUtilisateurConnecte(this.utilisateurConnecte);
            evolController.setGestionUtilisateur(this.gestionUtilisateur);
            evolController.updateData4();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherevolution1(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioEvolution.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = evolution.getScene();

            PortfolioEvolutionController evolController = loader.getController();
            evolController.setUtilisateurConnecte(this.utilisateurConnecte);
            evolController.setGestionUtilisateur(this.gestionUtilisateur);
            evolController.updateData4();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherevolution2(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/PortfolioEvolution.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = evolution.getScene();

            PortfolioEvolutionController evolController = loader.getController();
            evolController.setUtilisateurConnecte(this.utilisateurConnecte);
            evolController.setGestionUtilisateur(this.gestionUtilisateur);
            evolController.updateData5();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Date(){
        SimpleDateFormat sdf =new SimpleDateFormat("dd MMMM yyyy");
        String datenow =sdf.format(new Date());
        time.setText(datenow);

    }

    public void setGestionUtilisateur(GestionUtilisateur gestionUtilisateur) {
    }

    public void updateData2(){
        String symboleMonnaie = "eur".equals(currency) ? "€" : "$";

        double valeurTotale;
        String identifiant;

        if (this.utilisateurConnecte.getPortefeuilles().isEmpty() || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant() == null || this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant().isEmpty()) {
            valeurTotale = 0;
            identifiant = "N/A";
        } else {
            valeurTotale = this.utilisateurConnecte.getPortefeuilles().isEmpty() ? 0 : this.utilisateurConnecte.getPortefeuilles().get(0).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant());

            identifiant = this.utilisateurConnecte.getPortefeuilles().get(0).getIdentifiant();
        }

        this.ValeurTotale.setText(symboleMonnaie + " " + (valeurTotale == 0 ? "0.00" : String.format("%.2f", valeurTotale)));
        this.id.setText(identifiant);

        if (this.utilisateurConnecte.getPortefeuilles().size() > 1) {
            double valeurTotale2 = this.utilisateurConnecte.getPortefeuilles().get(1).getValeurTotaleFromCSV(this.utilisateurConnecte.getPortefeuilles().get(1).getIdentifiant());
            String identifiant2 = this.utilisateurConnecte.getPortefeuilles().get(1).getIdentifiant();
            this.ValeurTotale2.setText(symboleMonnaie + " " + String.format("%.2f", valeurTotale2));
            this.id2.setText(identifiant2);
        } else {
            // Gérez le cas où il n'y a pas de second portefeuille
            this.ValeurTotale2.setText(symboleMonnaie + " 0.00");
            this.id2.setText("N/A");
        }
    }
}



