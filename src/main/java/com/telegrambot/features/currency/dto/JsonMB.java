
package com.telegrambot.features.currency.dto;

import lombok.Data;

@Data
public class JsonMB {

    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private float rateBuy;
    private float rateSell;


}
