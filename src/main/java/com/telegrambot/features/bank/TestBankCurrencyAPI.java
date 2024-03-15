package com.telegrambot.features.bank;


import com.telegrambot.features.bank.pb.PrivatBankCurrencyServise;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class TestBankCurrencyAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        PrivatBankCurrencyServise privatBankCurrencyServise = new PrivatBankCurrencyServise();
        HashMap<String, Float> currencyTest = new HashMap<>();
        currencyTest = privatBankCurrencyServise.getRate();
        System.out.println("currencyTest.get(\"USD\" + \"pb\" + \"Buy\") = " + currencyTest.get("USD" + "pb" + "Buy"));
        System.out.println("currencyTest.get(\"USD\" + \"pb\" + \"Sale\") = " + currencyTest.get("USD" + "pb" + "Sale"));
        System.out.println("currencyTest.get(\"EUR\" + \"pb\" + \"Buy\") = " + currencyTest.get("EUR" + "pb" + "Buy"));
        System.out.println("currencyTest.get(\"EUr\" + \"pb\" + \"Sale\") = " + currencyTest.get("EUR" + "pb" + "Sale"));
    }

    }

