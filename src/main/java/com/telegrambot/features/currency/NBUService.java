package com.telegrambot.features.currency;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.currency.dto.CurrencyNBUItem;
import com.telegrambot.features.model.Bank;
import com.telegrambot.features.telegram.BotConstants;
import org.jsoup.Jsoup;

import static com.telegrambot.features.telegram.BotConstants.NBU_API_URL;


public class NBUService extends Bank implements BankService {

    public NBUService() {
        super("NBU");
    }

    @Override
    public double getBuyRate(Currency currency) {
        List<CurrencyNBUItem> currencyItems = getRatesFromNBU(currency);

        double converted = 0;

        if (currency.name().equalsIgnoreCase("USD")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getСс().equalsIgnoreCase("USD"))
                    .mapToDouble(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        } else if (currency.name().equalsIgnoreCase("EUR")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getСс().equalsIgnoreCase("EUR"))
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

    private List<CurrencyNBUItem> getRatesFromNBU(Currency currency) {
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

        Type typeToken = TypeToken.getParameterized(List.class, CurrencyNBUItem.class).getType();

        return new Gson().fromJson(json, typeToken);
    }

}
