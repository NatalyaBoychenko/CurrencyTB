
package com.telegrambot.features.currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.currency.dto.JsonMB;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import static com.telegrambot.features.telegram.BotConstants.MONO_URL;

//edit
public class MonoBankCurrencyService implements BankService {

    @Override
    public double getBuyRate(Currency currency) {

        List<JsonMB> currencyItems = getRate();
        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(JsonMB::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(JsonMB::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }

    @Override
    public double getSellRate(Currency currency) {
        List<JsonMB> currencyItems = getRate();

        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(JsonMB::getRateSell)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(JsonMB::getRateSell)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }


    private List<JsonMB> getRate() {

        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(MONO_URL)).GET().build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        JsonMB[] todosArray = gson.fromJson(response.body(), JsonMB[].class);
        return new ArrayList(Arrays.asList(todosArray));

    }

}
