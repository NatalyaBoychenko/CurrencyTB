package com.telegrambot.features.currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.currency.dto.CurrencyPrivatItem;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class PrivatBankCurrencyService extends Bank {


    public PrivatBankCurrencyService() {
        super("PrivatBank");
    }



    @Override
    public double getBuyRate(Currency currency) {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(CurrencyPrivatItem::getBuy)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public double getSellRate(Currency currency) {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(CurrencyPrivatItem::getSale)
                .findFirst()
                .orElseThrow();
    }

    public List<CurrencyPrivatItem> getCurrenciesOfBank() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        CurrencyPrivatItem[] todosArray = gson.fromJson(response.body(), (Type) CurrencyPrivatItem[].class);
        return new ArrayList<>(Arrays.asList(todosArray));
    }
}
