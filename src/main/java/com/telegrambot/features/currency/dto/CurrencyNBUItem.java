package com.telegrambot.features.currency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyNBUItem {
    private int r030;
    private String txt;
    private float rate;
    private String cc;
    @JsonProperty(value = "exchangedate")
    private String exchangeDate;
}
