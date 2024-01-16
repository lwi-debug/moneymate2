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
    private static Portefeuille1 convertirEnPortefeuille(String ligneCsv) {
        String[] data = ligneCsv.split(",");

        if (data.length < 5) {
            // Gérer l'erreur ou retourner null si la ligne n'est pas valide
            System.out.println("Ligne CSV invalide: " + ligneCsv);
            return null;
        }

        String emailUtilisateur = data[0];
        String identifiant = data[1];
        double valeurTotaleLiquidites;
        double valeurTotaleCryptos;
        double valeurTotaleActions;

        try {
            valeurTotaleLiquidites = Double.parseDouble(data[2]);
            valeurTotaleCryptos = Double.parseDouble(data[3]);
            valeurTotaleActions = Double.parseDouble(data[4]);
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion
            System.out.println("Erreur lors de la conversion des données numériques: " + e.getMessage());
            return null;
        }

        // Créer le portefeuille avec les données extraites
        Portefeuille1 portefeuille = new Portefeuille1();
        portefeuille.setIdentifiant(identifiant);
        // Note: Vous devez gérer la façon d'ajouter les liquidités, cryptos, et actions dans le portefeuille

        // Retourner le portefeuille peuplé
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
        double valeurTotaleLiquidites = portefeuille.valeurTotaleLiquidites();
        double valeurTotaleCryptos = portefeuille.valeurTotaleCryptos();
        double valeurTotaleActions = portefeuille.valeurTotaleActions();

        return emailUtilisateur + "," + portefeuille.getIdentifiant() + ","
                + valeurTotaleLiquidites + "," + valeurTotaleCryptos + "," + valeurTotaleActions;
    }
}


