package com.telegrambot.features.bank;

import com.telegrambot.features.bank.pb.PrivatBankCurrencyServise;

import java.io.IOException;

public class TestBankCurrencyAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        PrivatBankCurrencyServise privatBankCurrencyServise = new PrivatBankCurrencyServise();
        System.out.println("privatBankCurrencyServise.getRate(Currency.USD) = " + privatBankCurrencyServise.getRate(Currency.USD));
        System.out.println("privatBankCurrencyServise.getRate(Currency.EUR) = " + privatBankCurrencyServise.getRate(Currency.EUR));
    }
}
