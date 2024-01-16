package com.example.moneymate2;


import java.io.IOException;

public class ApiStocksTest {
    public static void main(String[] args) {
        try {
            String ibmSymbol = "IBM";
            double ibmLiveValue = ApiStocks.getLiveStockValue(ibmSymbol);
            System.out.println("Valeur d'une action IBM: $" + ibmLiveValue);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error fetching live stock value for IBM");
        }
    }
}

