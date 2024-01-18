package com.example.moneymate2;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class CSVUtilisateurManager {
    private static final String CSV_FILE = "src/main/java/com/example/moneymate2/Données.csv";

    public static List<Utilisateur1> chargerUtilisateurs() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            return br.lines().map(CSVUtilisateurManager::convertirEnUtilisateur).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static Utilisateur1 convertirEnUtilisateur(String ligneCsv) {
        String[] data = ligneCsv.split(",");
        if (data.length < 4) {
            System.out.println("Ligne CSV invalide: " + ligneCsv);
            return null;
        }
        return new Utilisateur1(data[0], data[1], data[2], data[3]); // Nom, Prénom, Email, MotDePasse
    }

    public static void sauvegarderUtilisateurs(List<Utilisateur1> utilisateurs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Utilisateur1 utilisateur : utilisateurs) {
                bw.write(convertirEnCSV(utilisateur));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertirEnCSV(Utilisateur1 utilisateur) {
        return utilisateur.getNom() + "," + utilisateur.getPrenom() + ","
                + utilisateur.getEmail() + "," + utilisateur.getMotDePasse();
    }
}
