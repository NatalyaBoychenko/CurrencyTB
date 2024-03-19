package com.telegrambot.features.telegram.util;

import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.currency.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.telegrambot.features.currency.dto.Currency.USD;
@Data
@AllArgsConstructor
public class Settings {
    private long chatId;
    private int roundDigit;
    private BankItem bank;
    private int reminderTime;
    private Currency currency;
    private static PrivatBankCurrencyService privat;

//    public Settings() {
//        privat = new PrivatBankCurrencyService();
//    }

    public static String getDefault() {
        privat = new PrivatBankCurrencyService();
        double buyRate = privat.getBuyRate(USD);
        double sellRate = privat.getSellRate(USD);

        return String.format("Курс в ПриватБанк: USD/UAN\nПокупка: %.2f\nПродаж: %.2f", buyRate, sellRate);
    }
//    new String(defaultMessage.getBytes(), StandardCharsets.UTF_8);

}
