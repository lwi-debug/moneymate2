package com.example.moneymate2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Crypto1 {
    private String symbole;
    private double prixUnitaire;
    private double quantite;
    private double prixAchat;

    public Crypto1(String symbole, double prixUnitaire, double quantite) {
        this.symbole = symbole;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.prixAchat = 0.0; // Initialisez-le à une valeur par défaut
    }
    public boolean isEmpty() {
        // Ajoutez ici la logique pour déterminer si Crypto1 est considéré comme vide
        // Par exemple, vous pourriez vérifier si tous les champs sont nuls ou vides
        return symbole == null || symbole.isEmpty() && prixUnitaire <= 0 && quantite <= 0;
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

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }



    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    // Propriétés pour JavaFX
    public StringProperty symboleProperty() {
        return new SimpleStringProperty(symbole);
    }

    public DoubleProperty prixUnitaireProperty() {
        return new SimpleDoubleProperty(prixUnitaire);
    }

    public DoubleProperty quantiteProperty() {
        return new SimpleDoubleProperty(quantite);
    }

    public DoubleProperty prixAchatProperty() {
        return new SimpleDoubleProperty(prixAchat);
    }
}
