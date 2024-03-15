package com.telegrambot.features.bank.nbu;
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

public class NBUCurrencyServise implements BankService {

    @Override
    public double getBuyRate(Currency currency) throws IOException, InterruptedException {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCc() == currency)
                .map(x-> x.getRate())
                .findFirst()
                .orElseThrow();
    }

    @Override
    public double getSellRate(Currency currency) throws IOException, InterruptedException {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCc() == currency)
                .map(x-> x.getRate())
                .findFirst()
                .orElseThrow();
    }

    public List<JsonNBU> getCurrenciesOfBank() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        //Get JSON
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        //Convert json => Java Object

        JsonNBU[] todosArray = gson.fromJson(response.body(), (Type) JsonNBU[].class);

        List<JsonNBU> curencyList = new ArrayList<>(Arrays.asList(todosArray));
        return curencyList;
    }


}
