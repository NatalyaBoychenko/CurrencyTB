package com.telegrambot.features.bank;

import java.io.IOException;

public interface CurrencyServise {
    double getRate (Currency currency) throws IOException, InterruptedException;
}
