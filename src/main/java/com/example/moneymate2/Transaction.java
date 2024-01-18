package com.example.moneymate2;

import java.time.LocalDateTime;

public class Transaction {
    private String assetSymbol;
    private double quantity;
    private double unitPrice;
    private LocalDateTime timestamp;

    public Transaction(String assetSymbol, double quantity, double unitPrice) {
        this.assetSymbol = assetSymbol;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.timestamp = LocalDateTime.now();
    }

    // Getters pour les attributs
    public String getAssetSymbol() {
        return assetSymbol;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
