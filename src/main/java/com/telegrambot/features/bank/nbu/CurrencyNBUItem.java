package com.telegrambot.features.bank.nbu;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CurrencyNBUItem {
    @SerializedName("r030")
    private int r030;
    @SerializedName("txt")
    private String txt;
    @SerializedName("rate")
    private float rate;
    @SerializedName("cc")
    private String cc;
    @SerializedName("exchangedate")
    private String exchangedate;
}
