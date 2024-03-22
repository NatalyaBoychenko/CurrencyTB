package com.telegrambot.features.currency;


import com.telegrambot.features.currency.dto.Currency;

public interface BankService {


    double getBuyRate(Currency currency);
    double getSellRate(Currency currency);
}
