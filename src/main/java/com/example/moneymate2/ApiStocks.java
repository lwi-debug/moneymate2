package com.example.moneymate2;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class ApiStocks {
    private static final String ALPHA_VANTAGE_API_KEY = "2AV5UQVR7TXVCP8L";
    private static final String API_URL_TEMPLATE =
            "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=1min&apikey=" + ALPHA_VANTAGE_API_KEY;

    public static double getLiveStockValue(String stockSymbol) throws IOException {
        String apiUrl = String.format(API_URL_TEMPLATE, stockSymbol);

        // Make API request and parse JSON response
        JSONObject stockData = getStockData(apiUrl);

        if (!stockData.has("Meta Data")) {
            System.out.println("Crypto '" + stockSymbol + "' non trouvée. Meta Data absente.");
            return 0;
        }

        String lastRefreshed;
        try {
            lastRefreshed = stockData.getJSONObject("Meta Data").getString("3. Last Refreshed");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'obtention des dernières données actualisées pour '" + stockSymbol + "': " + e.getMessage());
            return 0;
        }

        if (!stockData.has("Time Series (1min)")) {
            System.out.println("Série temporelle manquante pour '" + stockSymbol + "'.");
            return 0;
        }

        JSONObject timeSeriesData = stockData.getJSONObject("Time Series (1min)");
        if (!timeSeriesData.has(lastRefreshed)) {
            System.out.println("Aucune donnée trouvée pour la dernière actualisation pour '" + stockSymbol + "'.");
            return 0;
        }

        double latestPrice;
        try {
            latestPrice = timeSeriesData.getJSONObject(lastRefreshed).getDouble("4. close");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'obtention du dernier prix pour '" + stockSymbol + "': " + e.getMessage());
            return 0;
        }

        return latestPrice;
    }

    private static JSONObject getStockData(String apiUrl) throws IOException {
        URL url = URI.create(apiUrl).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream()))) {
            scanner.useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";
            return new JSONObject(response);
        } finally {
            connection.disconnect();
        }
    }
}
