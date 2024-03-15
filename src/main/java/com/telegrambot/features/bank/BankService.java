package com.telegrambot.features.bank;


import java.io.IOException;

public interface BankService {

        double getBuyRate(Currency currency) throws IOException, InterruptedException;
        double getSellRate(Currency currency) throws IOException, InterruptedException;
    }


