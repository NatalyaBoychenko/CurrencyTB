package com.telegrambot.features.bank.nbu;

import com.telegrambot.features.bank.Currency;

public class JsonNBU {
    private int r030;
    private String txt;
    private float rate;
    private Currency cc;
    private String exchangeDate;

    public int getR030() {
        return r030;
    }

    public String getTxt() {
        return txt;
    }

    public float getRate() {
        return rate;
    }

    public Currency getCc() {
        return cc;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }
}
