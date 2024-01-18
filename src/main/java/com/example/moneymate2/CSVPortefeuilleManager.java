package com.example.moneymate2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVPortefeuilleManager {
    private static final String CSV_FILE_PORTEFEUILLE = "src/main/java/com/example/moneymate2/Portefeuilles.csv";

    public static void sauvegarderPortefeuilles(List<Portefeuille1> portefeuilles, String emailUtilisateur) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PORTEFEUILLE, true))) {
            for (Portefeuille1 portefeuille : portefeuilles) {
                bw.write(convertirEnCSV(portefeuille, emailUtilisateur));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Portefeuille1 convertirEnPortefeuille(String ligneCsv) {
        String[] data = ligneCsv.split(",");

        if (data.length < 8) { // Assurez-vous d'avoir suffisamment de données
            System.out.println("Ligne CSV invalide: " + ligneCsv);
            return null;
        }

        String emailUtilisateur = data[0];
        String identifiant = data[1];

        // Parsez les valeurs numériques (valeur totale, liquidités, etc.)
        double valeurTotalePortefeuille, valeurTotaleLiquidites, valeurTotaleCryptos, valeurTotaleActions;
        try {
            valeurTotalePortefeuille = Double.parseDouble(data[2]);
            valeurTotaleLiquidites = Double.parseDouble(data[3]);
            valeurTotaleCryptos = Double.parseDouble(data[4]);
            valeurTotaleActions = Double.parseDouble(data[5]);
        } catch (NumberFormatException e) {
            System.out.println("Erreur lors de la conversion des données numériques: " + e.getMessage());
            return null;
        }

        // Créez un nouveau portefeuille
        Portefeuille1 portefeuille = new Portefeuille1();
        portefeuille.setIdentifiant(identifiant);

        // Ajoutez les liquidités
        Liquidité1 liquidite = new Liquidité1(valeurTotaleLiquidites);
        portefeuille.ajouterLiquidite(liquidite);

        // Parsez et ajoutez les cryptos
        String[] cryptoData = data[6].split(":"); // Supposons que le format soit "symbole:quantité"
        if (cryptoData.length == 2) {
            String symboleCrypto = cryptoData[0];
            double quantiteCrypto = Double.parseDouble(cryptoData[1]);
            Crypto1 crypto = new Crypto1(symboleCrypto, 0, quantiteCrypto); // 0 pour prix unitaire par défaut
            portefeuille.ajouterCrypto(crypto);
        }

        // Parsez et ajoutez les actions
        String[] actionData = data[7].split(":"); // Supposons que le format soit "symbole:quantité"
        if (actionData.length == 2) {
            String symboleAction = actionData[0];
            double quantiteAction = Double.parseDouble(actionData[1]);
            Action1 action = new Action1(symboleAction, 0, quantiteAction); // 0 pour prix unitaire par défaut
            portefeuille.ajouterAction(action);
        }

        // Calculez la valeur totale pour vérifier la cohérence
        double valeurCalculee = portefeuille.calculerValeurTotalePortefeuille();
        if (valeurCalculee > valeurTotalePortefeuille) {
            System.out.println("La valeur du portefeuille a augmenté depuis la dernière sauvegarde.");
        } else if (valeurCalculee < valeurTotalePortefeuille) {
            System.out.println("La valeur du portefeuille a diminué depuis la dernière sauvegarde.");
        } else {
            System.out.println("La valeur du portefeuille est restée la même depuis la dernière sauvegarde.");
        }

        return portefeuille;
    }



    public static List<Portefeuille1> chargerPortefeuilles(String emailUtilisateur) {
        List<Portefeuille1> portefeuilles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PORTEFEUILLE))) {
            portefeuilles = br.lines()
                    .filter(line -> line.startsWith(emailUtilisateur))
                    .map(CSVPortefeuilleManager::convertirEnPortefeuille)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return portefeuilles;
    }
    private static String convertirEnCSV(Portefeuille1 portefeuille, String emailUtilisateur) {
        StringBuilder builder = new StringBuilder();
        builder.append(emailUtilisateur).append(",")
                .append(portefeuille.getIdentifiant()).append(",")
                .append(portefeuille.getValeurTotalePortefeuille()).append(",")
                .append(portefeuille.valeurTotaleLiquidites()).append(",")
                .append(portefeuille.valeurTotaleCryptos()).append(",")
                .append(portefeuille.valeurTotaleActions());

        // Ajouter les informations de crypto
        // Ajouter les informations de crypto
        for (Crypto1 crypto : portefeuille.getCryptos()) { // Utilisation de getCryptos() au lieu de getCrypto1()
            builder.append(",").append(crypto.getSymbole())
                    .append(":").append(crypto.getQuantite());
        }

// Ajouter les informations d'actions
        for (Action1 action : portefeuille.getActions()) { // Utilisation de getActions() au lieu de getAction1()
            builder.append(",").append(action.getSymbole())
                    .append(":").append(action.getQuantite());
        }


        return builder.toString();
    }

}


