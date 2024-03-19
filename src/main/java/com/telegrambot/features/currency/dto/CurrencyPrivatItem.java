package com.telegrambot.features.currency.dto;

import lombok.Data;

@Data
public class CurrencyPrivatItem {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;
}
