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

    private static Utilisateur1 convertirEnUtilisateur(String line) {
        String[] data = line.split(",");
        return new Utilisateur1(data[0], data[1], data[2], data[3]); // Assure-toi que l'ordre correspond
    }

    private static String convertirEnCSV(Utilisateur1 utilisateur) {
        return utilisateur.getNom() + "," + utilisateur.getPrenom() + "," + utilisateur.getEmail() + "," + utilisateur.getMotDePasse();
    }
}

