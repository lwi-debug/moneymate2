package com.example.moneymate2;

public class Action1 {
    private String symbole;
    private double prixUnitaire;
    private double quantite;

    public Action1(String symbole, double prixUnitaire, double quantite) {
        this.symbole = symbole;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    // Getters et setters
    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}

