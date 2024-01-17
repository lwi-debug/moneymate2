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

        if (data.length < 6) {
            System.out.println("Ligne CSV invalide: " + ligneCsv);
            return null;
        }

        String emailUtilisateur = data[0];
        String identifiant = data[1];
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

        Portefeuille1 portefeuille = new Portefeuille1();
        portefeuille.setIdentifiant(identifiant);
        // Ici, ajoutez la logique pour ajouter les liquidités, cryptos et actions selon les valeurs lues
        // Remarque : Vous devez établir une correspondance entre les données du CSV et la structure de votre portefeuille

        // Après avoir ajouté les liquidités, cryptos et actions, calculez la valeur totale pour vérifier la cohérence
        portefeuille.calculerValeurTotalePortefeuille();

        // Vérifiez si la valeur totale calculée correspond à celle lue du CSV
        if (valeurTotalePortefeuille != portefeuille.getValeurTotalePortefeuille()) {
            System.out.println("Incohérence des données : La valeur totale du portefeuille ne correspond pas.");
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
        double valeurTotalePortefeuille = portefeuille.getValeurTotalePortefeuille();
        double valeurTotaleLiquidites = portefeuille.valeurTotaleLiquidites();
        double valeurTotaleCryptos = portefeuille.valeurTotaleCryptos();
        double valeurTotaleActions = portefeuille.valeurTotaleActions();

        return emailUtilisateur + "," +
                portefeuille.getIdentifiant() + "," +
                valeurTotalePortefeuille + "," +
                valeurTotaleLiquidites + "," +
                valeurTotaleCryptos + "," +
                valeurTotaleActions;
    }
}


