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
        String lastRefreshed = stockData.getJSONObject("Meta Data").getString("3. Last Refreshed");
        double latestPrice = stockData.getJSONObject("Time Series (1min)")
                .getJSONObject(lastRefreshed)
                .getDouble("4. close");

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
