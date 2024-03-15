package com.telegrambot.features.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public interface CurrencyServise {
    HashMap<String, Float> getRate () throws IOException, InterruptedException;

}
