package com.telegrambot.features.model;

import com.telegrambot.features.currency.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Bank {
    private String name;

    public abstract double getBuyRate(Currency currency);
    public abstract double getSellRate(Currency currency);
}
