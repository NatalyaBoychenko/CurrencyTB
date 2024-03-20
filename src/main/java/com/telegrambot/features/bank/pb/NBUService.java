package com.telegrambot.features.bank.pb;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Currency;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.telegrambot.features.model.Bank;
import org.jsoup.Jsoup;
import lombok.Data;

public class NBUService extends Bank implements CurrencyService {

    private static final String NBU_API_URL = "https://ваш_адрес_для_запросов_к_апи_NBU";

    public NBUService() {
        super("NBU");
    }

    @Override
    public double getBuyRate(Currency currency) {
        List<CurrencyNBUItem> currencyItems = getRate(currency);
        double converted = 0;
        if (currency.getCurrencyCode().equalsIgnoreCase("USD")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCc().equalsIgnoreCase("USD")) // Змінено згідно нового поля cc
                    .mapToDouble(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        } else if (currency.getCurrencyCode().equalsIgnoreCase("EUR")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCc().equalsIgnoreCase("EUR")) // Змінено згідно нового поля cc
                    .mapToDouble(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        }
        return converted;
    }

    @Override
    public double getSellRate(Currency currency) {
        return getBuyRate(currency); // Використовуємо той самий метод, оскільки у НБУ зазвичай один курс для купівлі та продажу
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
    @Data
    public class CurrencyNBUItem {
        private int r030; // Поле для 'r030'
        private String txt; // Поле для 'txt'
        private float rate; // Поле для 'rate'
        private String сс; // Поле для 'cc'
        private String exchangedate; // Поле для 'exchangedate'
    }
}
