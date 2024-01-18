package com.example.moneymate2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Portefeuille1 {
    private String identifiant;
    private List<Liquidité1> liquidites;
    private List<Crypto1> cryptos;
    private List<Action1> actions;

    private double valeurTotalePortefeuille;

    public Portefeuille1() {
        this.identifiant = genererId();
        this.liquidites = new ArrayList<>();
        this.cryptos = new ArrayList<>();
        this.actions = new ArrayList<>();
    }
    public Crypto1 getCrypto1() {
        if (!this.cryptos.isEmpty()) {
            return this.cryptos.get(0);
        } else {
            return null;
        }
    }
    public Action1 getAction1() {
        if (!this.actions.isEmpty()) {
            return this.actions.get(0);
        } else {
            return null;
        }
    }
    public List<Liquidité1> getLiquidites() {
        return liquidites;
    }
    public double getValeurTotaleCryptos() {
        return valeurTotaleCryptos();
    }
    public static double getValeurTotaleCryptosFromCSV() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lastLine.isEmpty()) {
            String[] values = lastLine.split(",");
            if (values.length > 4) {
                try {
                    return Double.parseDouble(values[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la valeur en double: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }

    public double getValeurTotaleAction() {
        return valeurTotaleActions();
    }
    public static double getValeurTotaleActionFromCSV() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lastLine.isEmpty()) {
            String[] values = lastLine.split(",");
            if (values.length > 5) {
                try {
                    return Double.parseDouble(values[5]);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la valeur en double: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }


    public double getPourcentageValeurLiquidites() {
        return pourcentageValeurLiquidites();
    }

    // Getter pour le pourcentage de la valeur des cryptos
    public double getPourcentageValeurCryptos() {
        return pourcentageValeurCryptos();
    }

    public static double getPourcentageValeurCryptosFromCSV() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lastLine.isEmpty()) {
            String[] values = lastLine.split(",");
            if (values.length > 4) {
                try {
                    double valeurTotaleCryptos = Double.parseDouble(values[4]);
                    double valeurTotalePortefeuille = Double.parseDouble(values[2]);
                    if (valeurTotalePortefeuille != 0) {
                        return (valeurTotaleCryptos / valeurTotalePortefeuille) * 100;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la valeur en double: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }
    public static double getPourcentageValeurActionFromCSV() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lastLine.isEmpty()) {
            String[] values = lastLine.split(",");
            if (values.length > 4) {
                try {
                    double valeurTotaleCryptos = Double.parseDouble(values[5]);
                    double valeurTotalePortefeuille = Double.parseDouble(values[2]);
                    if (valeurTotalePortefeuille != 0) {
                        return (valeurTotaleCryptos / valeurTotalePortefeuille) * 100;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la valeur en double: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }


    // Getter pour le pourcentage de la valeur des actions
    public double getPourcentageValeurActions() {
        return pourcentageValeurActions();
    }

    public double getValeurTotale() {
        return calculerValeurTotalePortefeuille();
    }
    public static double getValeurTotaleFromCSV() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/moneymate2/Portefeuilles.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!lastLine.isEmpty()) {
            String[] values = lastLine.split(",");
            if (values.length > 2) {
                try {
                    return Double.parseDouble(values[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la valeur en double: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }


    private String genererId() {
        // Générer un UUID et le formatter en 4 blocs de 4 caractères
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16);
    }

    public String getId() {
        return identifiant;
    }

    public boolean estVide() {

        return liquidites.isEmpty() && cryptos.isEmpty() && actions.isEmpty();
    }




    public double valeurTotaleLiquidites() {
        double total = 0;
        for (Liquidité1 liquidite : liquidites) {
            total += liquidite.getMontant();
        }
        return total;
    }

    // Méthode pour ajouter de la liquidité
    public void ajouterLiquidite(Liquidité1 liquidite) {
        this.liquidites.add(liquidite);
    }

    public double valeurTotaleCryptos() {
        double total = 0;
        ApiCaller apiCaller = new ApiCaller();
        for (Crypto1 crypto : cryptos) {
            double prixUnitaire = apiCaller.getCryptoValue(crypto.getSymbole());
            if (prixUnitaire != -1) {
                total += prixUnitaire * crypto.getQuantite();
            } else {
                System.out.println("Erreur lors de la récupération du prix pour la crypto : " + crypto.getSymbole());
            }
        }
        return total;
    }

    // Méthode pour ajouter une crypto
    public void ajouterCrypto(Crypto1 crypto) {
        this.cryptos.add(crypto);
    }

    public double valeurTotaleActions() {
        double total = 0;
        ApiStocks apiStocks = new ApiStocks();
        for (Action1 action : actions) {
            try {
                double prixUnitaire = apiStocks.getLiveStockValue(action.getSymbole());
                total += prixUnitaire * action.getQuantite();
            } catch (IOException e) {
                System.out.println("Erreur lors de la récupération du prix de l'action : " + action.getSymbole());
                e.printStackTrace();
            }
        }
        return total;
    }
    public double calculerValeurTotalePortefeuille() {
        double totalLiquidites = valeurTotaleLiquidites();
        double totalCryptos = valeurTotaleCryptos();
        double totalActions = valeurTotaleActions();

        this.valeurTotalePortefeuille = totalLiquidites + totalCryptos + totalActions;
        return totalLiquidites;
    }
    public double pourcentageValeurLiquidites() {
        if (valeurTotalePortefeuille == 0) return 0;
        return Math.round((valeurTotaleLiquidites() / valeurTotalePortefeuille) * 100 * 100.0) / 100.0;
    }

    public double pourcentageValeurCryptos() {
        if (valeurTotalePortefeuille == 0) return 0;
        return Math.round((valeurTotaleCryptos() / valeurTotalePortefeuille) * 100 * 100.0) / 100.0;
    }

    public double pourcentageValeurActions() {
        if (valeurTotalePortefeuille == 0) return 0;
        return Math.round((valeurTotaleActions() / valeurTotalePortefeuille) * 100 * 100.0) / 100.0;
    }

    public double getValeurTotalePortefeuille() {
        return valeurTotalePortefeuille;
    }

    // Méthode pour ajouter une action
    public void ajouterAction(Action1 action) {
        this.actions.add(action);
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    // Getters et setters ici
}
