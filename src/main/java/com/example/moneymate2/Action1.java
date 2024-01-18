package com.example.moneymate2;

public class Action1 {
    private String symbole;
    private double prixUnitaire;
    private double quantite;
    private boolean empty;

    public Action1(String symbole, double prixUnitaire, double quantite) {
        this.symbole = symbole;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    // Getters et setters
    public String getSymbole() {
        return symbole;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public double getQuantite() {
        return quantite;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public boolean isEmpty() {
        return empty;
    }
}

