package com.example.moneymate2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilisateur1{
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private List<Portefeuille1> portefeuilles;

    public Utilisateur1(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.portefeuilles = new ArrayList<>();
    }
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public List<Portefeuille1> getPortefeuilles() {
        return this.portefeuilles;
    }


    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setPortefeuilles(List<Portefeuille1> portefeuilles) {
        this.portefeuilles = portefeuilles;
    }

    // Méthodes pour gérer les portefeuilles
    public void ajouterPortefeuille(Portefeuille1 portefeuille) {
        this.portefeuilles.add(portefeuille);
    }


}

