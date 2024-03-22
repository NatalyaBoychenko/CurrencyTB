
package com.telegrambot.features.bank.mb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telegrambot.features.bank.Currency;
import com.telegrambot.features.bank.CurrencyServise;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//edit
public class MonoBankCurrencyServise implements CurrencyServise {

     @Override
    public double getBuyRate(CurrencyList currency) {

        List<CurrencyMonoItem> currencyItems = getRate();
        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }

    @Override
    public double getSellRate(CurrencyList currency) {
        List<CurrencyMonoItem> currencyItems = getRate();

        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateSell)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateSell)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }


    private List<CurrencyMonoItem> getRate() {

        String json;
        try {
            json = Jsoup
                    .connect(MONO_URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect to Monobank API");
        }

        Type typeTocken = TypeToken
                .getParameterized(List.class, CurrencyMonoItem.class)
                .getType();
        List<CurrencyMonoItem> currencyItems = new Gson().fromJson(json, typeTocken);

        return currencyItems;
    }


    
    // public MonoBankCurrencyServise() {
    // }

    // public double getRate(Currency currency) throws IOException, InterruptedException {
    //     Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    //     HttpClient client = HttpClient.newHttpClient();
    //     String url = "https://api.monobank.ua/bank/currency";
    //     HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
    //     HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    //     JsonMB[] todosArray = (JsonMB[])gson.fromJson((String)response.body(), JsonMB[].class);
    //     List<JsonMB> curencyList = new ArrayList(Arrays.asList(todosArray));
    //     return (double)(Float)curencyList.stream().filter((x) -> {
    //         return x.getCcy() == currency;
    //     }).map((x) -> {
    //         return x.getBuy();
    //     }).findFirst().orElseThrow();
    // }
}
