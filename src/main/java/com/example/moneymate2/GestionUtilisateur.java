package com.example.moneymate2;

import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateur {

    private Utilisateur1 utilisateurConnecte;
    private List<Utilisateur1> utilisateurs;
    private GestionTransaction gestionTransaction;


    //recup lutilisateur dans le csv données
    public GestionUtilisateur() {
        this.utilisateurs = CSVUtilisateurManager.chargerUtilisateurs();
    }


    public boolean creerCompte(String nom, String prenom, String email, String motDePasse) {
        System.out.println("Création de compte en cours pour l'email : " + email);
        if (emailExiste(email)) {
            return false;
        }

        Utilisateur1 nouvelUtilisateur = new Utilisateur1(nom, prenom, email, motDePasse);
        utilisateurs.add(nouvelUtilisateur);
        System.out.println("Compte créé avec succès : " + email);
        CSVUtilisateurManager.sauvegarderUtilisateurs(utilisateurs);
        return true;
    }


    public Utilisateur1 connexion(String email, String motDePasse) {
        for (Utilisateur1 utilisateur : utilisateurs) {
            if (utilisateur.getEmail().equals(email) && utilisateur.getMotDePasse().equals(motDePasse)) {
                return utilisateur;
            }
        }
        return null;
    }

    public boolean emailExiste(String email) {
        for (Utilisateur1 utilisateur : utilisateurs) {
            if (utilisateur != null && utilisateur.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public void sauvegarderUtilisateur(Utilisateur1 utilisateur) {
        System.out.println("Mise à jour de l'utilisateur: " + utilisateur.getEmail());
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).getEmail().equals(utilisateur.getEmail())) {
                utilisateurs.set(i, utilisateur);
                break;
            }
        }
        CSVUtilisateurManager.sauvegarderUtilisateurs(utilisateurs);
    }
    public boolean acheterCrypto(Crypto1 crypto, double prix) {
        // Vérifier si l'utilisateur a assez de liquidités pour acheter
        double liquiditeDisponible = utilisateurConnecte.getPortefeuilles().get(0).valeurTotaleLiquidites();

        if (liquiditeDisponible >= prix) {
            // Effectuer l'achat
            // Déduire le montant des liquidités
            Liquidité1 nouvelleLiquidite = new Liquidité1(liquiditeDisponible - prix);
            utilisateurConnecte.getPortefeuilles().get(0).ajouterLiquidite(nouvelleLiquidite);

            // Ajouter la crypto à son portefeuille
            crypto.setQuantite(crypto.getQuantite() + (prix / crypto.getPrixUnitaire()));
            utilisateurConnecte.getPortefeuilles().get(0).ajouterCrypto(crypto);

            // Mettre à jour la valeur totale du portefeuille
            utilisateurConnecte.getPortefeuilles().get(0).calculerValeurTotalePortefeuille();

            // Ajouter une transaction si nécessaire
            Transaction transaction = new Transaction(crypto.getSymbole(), prix / crypto.getPrixUnitaire(), crypto.getPrixUnitaire());
            gestionTransaction.ajouterTransaction(transaction);

            return true;
        } else {
            return false;
        }
    }
}

