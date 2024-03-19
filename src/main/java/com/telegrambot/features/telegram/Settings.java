package com.telegrambot.features.telegram;

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
    private List<Currency> currencyList;

    public Settings(long chatId) {
        this.chatId = chatId;
    }

    public static String getDefault() {
        PrivatBankCurrencyService privat = new PrivatBankCurrencyService();
        double buyRate = privat.getBuyRate(USD);
        double sellRate = privat.getSellRate(USD);
        return String.format("Курс в ПриватБанк: USD/UAN\nПокупка: %.2f\nПродаж: %.2f", buyRate, sellRate);
    }

//    public static Settings getDefaultSettings(long chatId){
//        Settings defaultSetting = new Settings(chatId);
//        defaultSetting.setBank(new PrivatBankCurrencyService());
//        defaultSetting.setRoundDigit(2);
//        List<Currency> currencyList = new ArrayList<>();
//
//        currencyList.add(USD);
//        defaultSetting.setCurrencies(currencyList);
//        defaultSetting.setReminderTime(13);
//        return defaultSetting;
//    }

}
