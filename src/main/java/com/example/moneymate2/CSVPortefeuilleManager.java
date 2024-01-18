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




    private static String convertirEnCSV(Portefeuille1 portefeuille, String emailUtilisateur) {
        StringBuilder builder = new StringBuilder();
        builder.append(emailUtilisateur).append(",")
                .append(portefeuille.getIdentifiant()).append(",")
                .append(portefeuille.getValeurTotalePortefeuille()).append(",")
                .append(portefeuille.valeurTotaleLiquidites()).append(",")
                .append(portefeuille.valeurTotaleCryptos()).append(",")
                .append(portefeuille.valeurTotaleActions());

        for (Crypto1 crypto : portefeuille.getCryptos()) {
            builder.append(",").append(crypto.getSymbole())
                    .append(":").append(crypto.getQuantite());
        }
        for (Action1 action : portefeuille.getActions()) {
            builder.append(",").append(action.getSymbole())
                    .append(":").append(action.getQuantite());
        }
        return builder.toString();// mail, id, valeurtotaleportefeuille,valeur totale crypto,valeurtotaleactions,nom crypto: quantitécrypto, nom symbole action : quantité action
    }





    public static Portefeuille1 convertirEnPortefeuille(String ligneCsv) {
        String[] data = ligneCsv.split(",");

        if (data.length < 8) {
            System.out.println("Ligne CSV invalide: " + ligneCsv);
            return null;
        }

        String emailUtilisateur = data[0];
        String identifiant = data[1];

        double valeurTotalePortefeuille, valeurTotaleLiquidites, valeurTotaleCryptos, valeurTotaleActions;
        try {
            valeurTotalePortefeuille = Double.parseDouble(data[2]);
            valeurTotaleLiquidites = Double.parseDouble(data[3]);
            valeurTotaleCryptos = Double.parseDouble(data[4]); // peut etre utile pour plus de stat
            valeurTotaleActions = Double.parseDouble(data[5]);
        } catch (NumberFormatException e) {
            System.out.println("Erreur lors de la conversion des données numériques: " + e.getMessage());
            return null;
        }

        //nouveau portefeuille
        Portefeuille1 portefeuille = new Portefeuille1();
        portefeuille.setIdentifiant(identifiant);

        // conversion des liquidités,crypto et action
        Liquidité1 liquidite = new Liquidité1(valeurTotaleLiquidites);
        portefeuille.ajouterLiquidite(liquidite);

        String[] cryptoData = data[6].split(":");
        if (cryptoData.length == 2) {
            String symboleCrypto = cryptoData[0];
            double quantiteCrypto = Double.parseDouble(cryptoData[1]);
            Crypto1 crypto = new Crypto1(symboleCrypto, 0, quantiteCrypto);
            portefeuille.ajouterCrypto(crypto);
        }

        String[] actionData = data[7].split(":");
        if (actionData.length == 2) {
            String symboleAction = actionData[0];
            double quantiteAction = Double.parseDouble(actionData[1]);
            Action1 action = new Action1(symboleAction, 0, quantiteAction);
            portefeuille.ajouterAction(action);
        }

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
    // cette alternative est utilisé dans le boutton login lors de la reconstitution du portefeuille a partir des info du csv. permet la supression de la les ligne qui nous intéresse . cette methode fonctionne seulement avec le request api rate de geko coin nous appelons trop souvent la methode getcryptovalue dans apicall et donc nous obtenont lerreur 429. "

    /*public static void supprimerPortefeuille(String emailUtilisateur) {
        File inputFile = new File(CSV_FILE_PORTEFEUILLE);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith(emailUtilisateur + ",")) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Supprimer l'ancien fichier et renommer le fichier temporaire
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        }
    }*/

}


