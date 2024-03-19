package com.telegrambot.features.telegram.command;

import com.telegrambot.features.telegram.Settings;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;

public class Currency {
    Settings settings;

    public void handleCallbackCurrency(CallbackQuery callbackQuery) {
        settings = new Settings(callbackQuery.getMessage().getChatId());
        String answer = callbackQuery.getData();

        switch (answer) {
            case "EUR":
                settings.setCurrencyList(List.of(EUR, USD));
                System.out.println("successful eur");
                break;
            default:
                settings.setCurrencyList(List.of(USD));
                break;
        }
    }
}
