package com.example.moneymate2;

import java.util.ArrayList;
import java.util.List;

public class GestionTransaction {
    private List<Transaction> transactions;

    public GestionTransaction() {
        this.transactions = new ArrayList<>();
    }

    public boolean acheterActif(String assetSymbol, double quantity, double unitPrice, double liquiditeDisponible) {
        double montantTotal = quantity * unitPrice;

        // Vérifier si l'utilisateur a assez de liquidité pour acheter
        if (liquiditeDisponible >= montantTotal) {
            Transaction transaction = new Transaction(assetSymbol, quantity, unitPrice);
            transactions.add(transaction);
            System.out.println("Achat réussi : " + quantity + " " + assetSymbol + " à " + unitPrice + " chacun.");
            return true;
        } else {
            System.out.println("Fonds insuffisants pour effectuer l'achat.");
            return false;
        }
    }
    public void ajouterTransaction(Transaction nouvelleTransaction) {
        transactions.add(nouvelleTransaction);
        // Vous pouvez également effectuer d'autres opérations liées à l'ajout de transaction ici, si nécessaire
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Autres méthodes pour récupérer l'historique des transactions, etc.
}
