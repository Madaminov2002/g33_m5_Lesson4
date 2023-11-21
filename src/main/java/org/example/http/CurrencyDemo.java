package org.example.http;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CurrencyDemo {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://nbu.uz/en/exchange-rates/json/"))
                .GET()
                .build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        Type type = new TypeToken<List<Currency>>() {
        }.getType();
        List<Currency> list = new GsonBuilder().create().fromJson(send.body(), type);
        list.forEach(currency -> currency.setCbPrice(currency.getCbPrice()));


    }
}
