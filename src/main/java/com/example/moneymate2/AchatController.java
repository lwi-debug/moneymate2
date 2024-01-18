/*package com.example.moneymate2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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


        // Exemple pour Bitcoin (BTC)
        double bitcoinPrice = apiCaller.getCryptoValue("bitcoin");
        Crypto1 bitcoin = new Crypto1("Bitcoin", bitcoinPrice, 0);
        topCryptos.add(bitcoin);

        // Répétez le processus pour les autres cryptomonnaies
        double ethereumPrice = apiCaller.getCryptoValue("ethereum");
        Crypto1 ethereum = new Crypto1("Ethereum", ethereumPrice, 0);
        topCryptos.add(ethereum);

        // Ajoutez les trois autres cryptomonnaies de manière similaire

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
}
*/


