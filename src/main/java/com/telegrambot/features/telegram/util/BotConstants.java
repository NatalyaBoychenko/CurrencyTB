package com.telegrambot.features.telegram.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

@UtilityClass
public class BotConstants {

    public static final String BOT_NAME = "";
    public static final String BOT_TOKEN = "";
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";


    public static String getNameButton(String language, String name){
        Locale locale = new Locale(language);
        ResourceBundle rb = ResourceBundle.getBundle("names", locale);
        return rb.getString(name);

    }
}
