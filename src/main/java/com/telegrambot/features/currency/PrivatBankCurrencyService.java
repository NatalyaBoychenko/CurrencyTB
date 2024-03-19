package com.telegrambot.features.currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.currency.dto.JsonPB;
import com.telegrambot.features.model.Bank;


import java.io.IOException;
import java.lang.reflect.Type;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static com.telegrambot.features.telegram.BotConstants.PRIVAT_URL;

public class PrivatBankCurrencyService extends Bank implements BankService {


    public PrivatBankCurrencyService() {
        super("ПриватБанк");
    }

    @Override
    public double getBuyRate(Currency currency)  {

         return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getBuy())
                .findFirst()
                .orElseThrow();
    }

    @Override
    public double getSellRate(Currency currency)  {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getSale())
                .findFirst()
                .orElseThrow();
    }
    public List<JsonPB> getCurrenciesOfBank()  {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = PRIVAT_URL;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
            JsonPB[] todosArray = gson.fromJson(response.body(), (Type) JsonPB[].class);
            List<JsonPB> curencyList = new ArrayList<>(Arrays.asList(todosArray));
        return curencyList;
    }

}