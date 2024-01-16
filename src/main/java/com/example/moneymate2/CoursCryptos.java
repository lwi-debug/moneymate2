package com.example.moneymate2;



import java.io.Serializable;

public class CoursCryptos implements Serializable {
    private String id;
    private String Image;
    private String name;
    private double currentPrice;
    private double low24h;  // Prix le plus bas des dernières 24 heures
    private double high24h; // Prix le plus élevé des dernières 24 heures
    // d'autres attributs...
    private String symbol;
    private Long marketCap;
    public CoursCryptos() {
        // Constructeur par défaut
    }
    public String getSymbol() {
        return symbol;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    // Getters et setters pour tous les attributs

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getLow24h() {
        return low24h;
    }

    public void setLow24h(double low24h) {
        this.low24h = low24h;
    }

    public double getHigh24h() {
        return high24h;
    }

    public void setHigh24h(double high24h) {
        this.high24h = high24h;
    }

    public String getImage() {
        return Image;
    }

    // D'autres getters et setters...
}
