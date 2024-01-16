package com.example.moneymate2;

public class ApiCallerTest {

    public static void main(String[] args) {
        ApiCaller apiCaller = new ApiCaller();

        // Test pour obtenir le prix en dollars du Bitcoin (BTC)
        String cryptoSymbol = "bitcoin";
        double btcPriceInUSD = apiCaller.getCryptoValue(cryptoSymbol);

        if (btcPriceInUSD != -1) {
            System.out.println("Le prix du " + cryptoSymbol.toUpperCase() + " en USD est : $" + btcPriceInUSD);
        } else {
            System.out.println("Erreur lors de la récupération du prix du " + cryptoSymbol.toUpperCase());
        }
    }
}
