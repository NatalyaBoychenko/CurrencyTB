package com.telegrambot.features.currency;

import com.telegrambot.features.currency.dto.Currency;
import lombok.Data;

@Data
public abstract class Bank {
    private String name;
    public abstract double getBuyRate(Currency currency);
    public abstract double getSellRate(Currency currency);
}
