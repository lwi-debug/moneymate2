package com.example.moneymate2;

public class TestPortefeuille {

    public static void main(String[] args) {
        // Créer une instance de la classe Portefeuille1
        Portefeuille1 portefeuille = new Portefeuille1();

        // Appeler la nouvelle méthode pour récupérer la valeur des liquidités depuis le fichier CSV
        double valeurLiquidites = portefeuille.getValeurLiquiditesFromCSV();

        // Afficher la valeur des liquidités
        System.out.println("Valeur des liquidités dans le fichier CSV : " + valeurLiquidites);
    }
}
