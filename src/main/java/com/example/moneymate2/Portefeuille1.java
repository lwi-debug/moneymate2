package com.example.moneymate2;

import java.util.ArrayList;
import java.util.List;

public class Portefeuille1 {
    private List<Liquidité1> liquidites;
    private List<Crypto1> cryptos;
    private List<Action1> actions;

    public Portefeuille1() {
        this.liquidites = new ArrayList<>();
        this.cryptos = new ArrayList<>();
        this.actions = new ArrayList<>();
    }
    public boolean estVide() {
        return liquidites.isEmpty() && cryptos.isEmpty() && actions.isEmpty();
    }
    public void ajouterLiquidite(Liquidité1 liquidite) {
        liquidites.add(liquidite);
    }

    public void ajouterCrypto(Crypto1 crypto) {
        cryptos.add(crypto);
    }

    public void ajouterAction(Action1 action) {
        actions.add(action);
    }
    // Getters et setters ici
}

