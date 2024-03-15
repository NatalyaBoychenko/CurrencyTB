package com.telegrambot.features.bank;


import com.telegrambot.features.bank.nbu.NBUCurrencyServise;
import com.telegrambot.features.bank.pb.PrivatBankCurrencyServise;
import com.telegrambot.features.currency.dto.Currency;

import java.io.IOException;


public class TestBankCurrencyAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        PrivatBankCurrencyServise privatBankCurrencyServise = new PrivatBankCurrencyServise();
        System.out.println("privatBankCurrencyServise.getBuyRate(Currency.USD) = " + privatBankCurrencyServise.getBuyRate(Currency.USD));
        System.out.println("privatBankCurrencyServise.getSellRate(Currency.USD) = " + privatBankCurrencyServise.getSellRate(Currency.USD));
        System.out.println("privatBankCurrencyServise.getBuyRate(Currency.EUR) = " + privatBankCurrencyServise.getBuyRate(Currency.EUR));
        System.out.println("privatBankCurrencyServise.getSellRate(Currency.EUR) = " + privatBankCurrencyServise.getSellRate(Currency.EUR));
        NBUCurrencyServise nbuCurrencyServise = new NBUCurrencyServise();
        System.out.println("nbuCurrencyServise.getBuyRate(Currency.USD) = " + nbuCurrencyServise.getBuyRate(Currency.USD));
        System.out.println("nbuCurrencyServise.getSellRate(Currency.USD) = " + nbuCurrencyServise.getSellRate(Currency.USD));
        System.out.println("nbuCurrencyServise.getBuyRate(Currency.EUR) = " + nbuCurrencyServise.getBuyRate(Currency.EUR));
        System.out.println("nbuCurrencyServise.getSellRate(Currency.EUR) = " + nbuCurrencyServise.getSellRate(Currency.EUR));
    }

    }

