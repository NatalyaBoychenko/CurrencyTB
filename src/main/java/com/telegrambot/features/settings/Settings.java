package com.telegrambot.features.settings;

import com.telegrambot.features.currency.Bank;
import com.telegrambot.features.currency.PrivatBankCurrencyService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import com.telegrambot.features.currency.dto.Currency;
import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.USD;

@Data
@AllArgsConstructor
public class Settings {

    private long chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Currency> currencies;

    public Settings(long chatId) {
        this.chatId = chatId;
    }


    public static Settings getDefaultSettings(long chatId){

        Settings defaultSettings = new Settings(chatId);
        defaultSettings.setBank(new PrivatBankCurrencyService());
        defaultSettings.setRoundDigit(2);
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(USD);
        defaultSettings.setCurrencies(currencyList);
        defaultSettings.setReminderTime(0);
        return defaultSettings;

    }
}
