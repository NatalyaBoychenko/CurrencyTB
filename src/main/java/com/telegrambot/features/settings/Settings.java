package com.telegrambot.features.settings;

import com.telegrambot.features.currency.Bank;
import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.currency.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
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
    private String language;

    public Settings(long chatId) {
        this.chatId = chatId;
    }

    public static Settings getDefaultSettings(long chatId, StorageInMemoryRepo storageInMemory){


        Settings defaultSettings = new Settings(chatId);
        storageInMemory.addSetting(chatId, defaultSettings);
        defaultSettings.setBank(new PrivatBankCurrencyService());
        defaultSettings.setRoundDigit(2);
        defaultSettings.setLanguage("eng");
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(USD);
        defaultSettings.setCurrencies(currencyList);
        defaultSettings.setReminderTime(26);
        return defaultSettings;

    }
}
