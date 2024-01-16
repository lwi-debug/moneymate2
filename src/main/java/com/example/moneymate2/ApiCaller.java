package com.example.moneymate2;


import com.example.moneymate2.CoursCryptos;
import com.example.moneymate2.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApiCaller {
    private static final String BASE_URL = "https://api.coingecko.com/api/v3";

    private static Gson gson = null;

    public ApiCaller() {

        this.gson = new Gson();
    }

    public static List<CoursCryptos> getAllCoinsMarket(String currency, int limit, String order, int page, boolean sparkline, String priceChangePercentage, String locale) {
        String url = BASE_URL + "/coins/markets?vs_currency=" + currency + "&order=" + order + "&per_page=" + limit + "&page=" + page + "&sparkline=" + sparkline
                + "&price_change_percentage=" + priceChangePercentage + "&locale=" + locale;
        String jsonResponse = makeApiCall(url);
        Type listType = new TypeToken<List<CoursCryptos>>() {}.getType();


        System.out.println("JSON Response from API: " + jsonResponse);

        List<CoursCryptos> cryptos = gson.fromJson(jsonResponse, listType);


        System.out.println("Cryptos after deserialization: " + cryptos);

        return cryptos;
    }


    public News getLatestNews() {
        String url = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
        String jsonResponse = makeApiCall(url);
        Type listType = new TypeToken<News>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public static String makeApiCall(String apiUrl) {
        try {
            // Créer l'URL de l'API
            URL url = new URL(apiUrl);

            // Ouvrir la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Définir la méthode de requête (GET dans cet exemple)
            connection.setRequestMethod("GET");

            // Obtenir la réponse de l'API
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lire la réponse de l'API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Retourner la réponse JSON
                return response.toString();
            } else {
                // Gérer les erreurs de l'API
                System.out.println("Erreur lors de la requête. Code de réponse : " + responseCode);
                return null;
            }
        } catch (IOException e) {
            // Gérer les exceptions d'entrée/sortie
            e.printStackTrace();
            return null;
        }
    }
    public double getCryptoValue(String cryptoSymbol) {
        try {
            // Construire l'URL pour obtenir le cours de la crypto en direct
            String url = BASE_URL + "/simple/price?ids=" + cryptoSymbol + "&vs_currencies=usd";
            String jsonResponse = makeApiCall(url);

            // Désérialiser la réponse JSON pour obtenir le prix de la crypto
            Type mapType = new TypeToken<Map<String, Map<String, Double>>>() {}.getType();
            Map<String, Map<String, Double>> cryptoPriceMap = gson.fromJson(jsonResponse, mapType);

            // Vérifier si la réponse contient la crypto demandée
            if (cryptoPriceMap.containsKey(cryptoSymbol.toLowerCase())) {
                Map<String, Double> priceMap = cryptoPriceMap.get(cryptoSymbol.toLowerCase());
                if (priceMap.containsKey("usd")) {
                    return priceMap.get("usd");
                } else {
                    System.out.println("Le prix en USD n'a pas été trouvé dans la réponse JSON.");
                    return -1;
                }
            } else {
                System.out.println("La crypto " + cryptoSymbol + " n'a pas été trouvée dans la réponse JSON.");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}