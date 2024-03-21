package com.telegrambot.features.telegram;

import com.telegrambot.features.currency.Bank;
import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.telegram.util.SavedSettings;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.USD;
@Data
@AllArgsConstructor
public class Settings {
    private long chatId;
    private int roundDigit = 2;
    private Bank bank = new PrivatBankCurrencyService();
    private int reminderTime = 12;
    private List<Currency> currencyList = List.of(USD);

    public Settings(long chatId) {
        this.chatId = chatId;
    }

    public static String getDefault() {
        PrivatBankCurrencyService privat = new PrivatBankCurrencyService();
        double buyRate = privat.getBuyRate(USD);
        double sellRate = privat.getSellRate(USD);
        return String.format("Курс в ПриватБанк: USD/UAN\nПокупка: %.2f\nПродаж: %.2f", buyRate, sellRate);
    }

//    public static String getDefaultSettings(long chatId) {
//        Settings settingForUser = SavedSettings.getSettingForUser(chatId);
//        assert settingForUser != null;
//        Bank bank = settingForUser.getBank();
//        int reminder = settingForUser.getReminderTime();
////        List<Currency> currencyList = settingForUser.getCurrencyList();
//
//        int roundDigit = settingForUser.getRoundDigit();
//        double buyRate = bank.getBuyRate(USD);
//        double sellRate = bank.getSellRate(USD);
//        double scale = Math.pow(10, reminder);
//        return String.format("Курс в ПриватБанк: USD/UAN\nПокупка: %s\nПродаж: %s", buyRate, sellRate);
    }

