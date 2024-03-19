package com.telegrambot.features.model;

import com.telegrambot.features.bank.pb.PrivatBankCurrencyServise;
import lombok.Data;

import java.util.ArrayList;
import com.telegrambot.features.currency.dto.Currency;
import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.USD;

@Data
public class Setting {

    private long chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Currency> currencies;

    public Setting(long chatId) {
        this.chatId = chatId;
    }


    public Setting(long chatId, int roundDigit, Bank bank, int reminderTime, List<Currency> currencies) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currencies = currencies;
    }

    public static Setting getDefaultSettings(long chatId){

        Setting defaultSetting = new Setting(chatId);
        defaultSetting.setBank(new PrivatBankCurrencyServise());
        defaultSetting.setRoundDigit(2);
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(USD);
        defaultSetting.setCurrencies(currencyList);
        defaultSetting.setReminderTime(13);
        return defaultSetting;

    }
}
