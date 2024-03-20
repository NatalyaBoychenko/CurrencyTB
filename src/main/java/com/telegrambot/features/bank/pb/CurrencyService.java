package com.telegrambot.features.bank.pb;

import java.util.Currency;

public interface CurrencyService {
    double getBuyRate(Currency currency);

    double getSellRate(Currency currency);
}
