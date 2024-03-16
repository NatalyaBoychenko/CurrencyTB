package com.telegrambot.features.bank.pb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.currency.BankService;
import com.telegrambot.features.currency.dto.Currency;


import java.io.IOException;
import java.lang.reflect.Type;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class PrivatBankCurrencyServise implements BankService {

    @Override
    public double getBuyRate(Currency currency) throws IOException, InterruptedException {

         return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getBuy())
                .findFirst()
                .orElseThrow();
    }

    @Override
    public double getSellRate(Currency currency) throws IOException, InterruptedException {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getSale())
                .findFirst()
                .orElseThrow();
    }

    public List<JsonPB> getCurrenciesOfBank() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        //Get JSON
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        //Convert json => Java Object
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonPB[] todosArray = gson.fromJson(response.body(), (Type) JsonPB[].class);
            List<JsonPB> curencyList = new ArrayList<>(Arrays.asList(todosArray));



        return curencyList;
    }


}
