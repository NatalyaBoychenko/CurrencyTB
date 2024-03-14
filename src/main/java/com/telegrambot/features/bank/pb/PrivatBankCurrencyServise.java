package com.telegrambot.features.bank.pb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.bank.Currency;
import com.telegrambot.features.bank.CurrencyServise;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrivatBankCurrencyServise implements CurrencyServise {
    @Override
    public double getRate(Currency currency) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        //Get JSON
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Convert json => Java Object

        JsonPB[] todosArray = gson.fromJson(response.body(), (Type) JsonPB[].class);
        List<JsonPB> curencyList = new ArrayList<>(Arrays.asList(todosArray));

        //Find USD/EUR

        return curencyList.stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getBuy())
                .findFirst()
                .orElseThrow();
    }
}

