package ru.tinkoff.edu.java.scrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpClient {
    private final String baseUrl;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String sendGetRequest(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine1;
        StringBuilder response = new StringBuilder();

        while ((inputLine1 = in.readLine()) != null) {
            response.append(inputLine1);
        }

        in.close();
        connection.disconnect();

        return response.toString();
    }
}