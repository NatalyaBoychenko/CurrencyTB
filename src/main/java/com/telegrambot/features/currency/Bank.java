package com.telegrambot.features.currency;

import com.telegrambot.features.currency.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Bank {
String name;
    public abstract double getBuyRate(Currency currency);
    public abstract double getSellRate(Currency currency);
}
