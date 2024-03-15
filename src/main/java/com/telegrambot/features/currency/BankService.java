package com.telegrambot.features.currency;


import com.telegrambot.features.currency.dto.Currency;

import java.io.IOException;

public interface BankService {

        double getBuyRate(Currency currency) throws IOException, InterruptedException;
        double getSellRate(Currency currency) throws IOException, InterruptedException;
    }


