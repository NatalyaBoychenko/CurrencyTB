package com.telegrambot.features.currency;

import com.telegrambot.features.currency.dto.Currency;

import java.io.IOException;

public interface CurrencyService {
    double getBuyRate(Currency currency) throws IOException;
    double getSellRate(Currency currency) throws IOException, InterruptedException;
}
