package com.telegrambot.features.bank;


import com.telegrambot.features.bank.pb.PrivatBankCurrencyServise;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class TestBankCurrencyAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        PrivatBankCurrencyServise privatBankCurrencyServise = new PrivatBankCurrencyServise();
        System.out.println("privatBankCurrencyServise.getBuyRate(Currency.USD) = " + privatBankCurrencyServise.getBuyRate(Currency.USD));
        System.out.println("privatBankCurrencyServise.getSellRate(Currency.USD) = " + privatBankCurrencyServise.getSellRate(Currency.USD));
        System.out.println("privatBankCurrencyServise.getBuyRate(Currency.EUR) = " + privatBankCurrencyServise.getBuyRate(Currency.EUR));
        System.out.println("privatBankCurrencyServise.getSellRate(Currency.EUR) = " + privatBankCurrencyServise.getSellRate(Currency.EUR));
    }

    }

