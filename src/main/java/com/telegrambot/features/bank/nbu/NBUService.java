package com.telegrambot.features.bank.nbu;

import java.util.List;

import com.telegrambot.features.bank.pb.CurrencyService;
import com.telegrambot.features.model.Bank;

import java.io.IOException;
import java.util.Currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

public abstract class NBUService extends Bank implements CurrencyService {

    private static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public NBUService() {
        super("NBU");
    }

    @Override
    public double getBuyRate(Currency currency) {
        List<CurrencyNBUItem> currencyItems = getRatesFromNBU();
        double converted = 0;
        if (currency.getCurrencyCode().equalsIgnoreCase("USD")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCc().equalsIgnoreCase("USD"))
                    .mapToDouble(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        } else if (currency.getCurrencyCode().equalsIgnoreCase("EUR")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCc().equalsIgnoreCase("EUR"))
                    .mapToDouble(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        }
        return converted;
    }

    @Override
    public double getSellRate(Currency currency) {
        return getBuyRate(currency);
    }

    private List<CurrencyNBUItem> getRatesFromNBU() {
        String json;
        try {
            json = Jsoup.connect(NBU_API_URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect to NBU API");
        }
        java.lang.reflect.Type typeToken = TypeToken.getParameterized(List.class, CurrencyNBUItem.class).getType();
        return new Gson().fromJson(json, typeToken);
    }
}
