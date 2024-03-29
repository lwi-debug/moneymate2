package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AchatController {

    @FXML
    private TableView<Crypto1> cryptoTable;

    @FXML
    private TableColumn<Crypto1, String> nameColumn;

    @FXML
    private TableColumn<Crypto1, Double> valueColumn;

    @FXML
    private TableColumn<Crypto1, Double> quantityColumn;

    @FXML
    private TableColumn<Crypto1, Double> buyPriceColumn;

    @FXML
    private TextField buyPriceField;

    @FXML
    private Label errorLabel;
    @FXML
    public Button dash;


    private Utilisateur1 utilisateurConnecte;
    private GestionUtilisateur gestionUtilisateur;
    private ApiCaller apiCaller;

    public void setUtilisateurConnecte(Utilisateur1 utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void setGestionUtilisateur(GestionUtilisateur gestion) {
        this.gestionUtilisateur = gestion;
    }

    @FXML
    public void initialize() {
        apiCaller = new ApiCaller();

        // Obtenez les informations sur les cinq cryptomonnaies les plus connues
        List<Crypto1> topCryptos = getTopCryptos();

        // Remplissez le tableau avec les informations obtenues
        cryptoTable.getItems().addAll(topCryptos);

        // Configurez les cellules de la table pour afficher les informations correctement
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().symboleProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().prixUnitaireProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
        buyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().prixAchatProperty().asObject());
    }
    @FXML
    void validerAchat(ActionEvent event) {
        try {
            double buyPrice = Double.parseDouble(buyPriceField.getText());

            // Affichez le prix pour confirmation (vous pouvez le faire dans une boîte de dialogue ou une étiquette)
            System.out.println("Prix confirmé : " + buyPrice);

            // Vous pouvez également activer ou désactiver le bouton d'achat ici en fonction des besoins
            // Par exemple, validerButton.setDisable(false);
        } catch (NumberFormatException e) {
            errorLabel.setText("Veuillez entrer un prix valide.");
        }
    }
    private List<Crypto1> getTopCryptos() {
        List<Crypto1> topCryptos = new ArrayList<>();



        double bitcoinPrice = apiCaller.getCryptoValue("bitcoin");
        Crypto1 bitcoin = new Crypto1("Bitcoin", bitcoinPrice, 0);
        topCryptos.add(bitcoin);

        // Répétez le processus pour les autres cryptomonnaies
        double ethereumPrice = apiCaller.getCryptoValue("ethereum");
        Crypto1 ethereum = new Crypto1("Ethereum", ethereumPrice, 0);
        topCryptos.add(ethereum);

        double solanaPrice = apiCaller.getCryptoValue("solana");
        Crypto1 solana = new Crypto1("Solana", solanaPrice, 0);
        topCryptos.add(solana);

        double cardanoPrice = apiCaller.getCryptoValue("cardano");
        Crypto1 cardano = new Crypto1("Cardano", cardanoPrice, 0);
        topCryptos.add(cardano);

        double xrpPrice = apiCaller.getCryptoValue("xrp");
        Crypto1 xrp = new Crypto1("XRP", xrpPrice, 0);
        topCryptos.add(xrp);



        return topCryptos;
    }

    @FXML
    void buyCrypto() {
        Crypto1 selectedCrypto = cryptoTable.getSelectionModel().getSelectedItem();

        if (selectedCrypto != null) {
            try {
                double buyPrice = Double.parseDouble(buyPriceField.getText());
                boolean achatReussi = gestionUtilisateur.acheterCrypto(selectedCrypto, buyPrice);

                if (achatReussi) {
                    errorLabel.setText("Achat réussi !");
                } else {
                    errorLabel.setText("Erreur lors de l'achat.");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Veuillez entrer un prix valide.");
            }
        } else {
            errorLabel.setText("Veuillez sélectionner une crypto.");
        }
    }
    @FXML
    void dashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moneymate2/Dashboard.fxml"));
            Parent settingsRoot = loader.load();
            Scene scene = dash.getScene();

            DashboardController setController = loader.getController();
            setController.setUtilisateurConnecte(this.utilisateurConnecte);
            setController.setGestionUtilisateur(this.gestionUtilisateur);

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(settingsRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



