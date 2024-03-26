package com.telegrambot.features.telegram.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@UtilityClass
public class BotConstants {

    public static final String BOT_NAME = "NatBoiCurBot";
    public static final String BOT_TOKEN = "6412532350:AAHp0kMrq78YDNXWT6M2zMOsgceuLDWauk8";
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    public static final List<String> TIME_REMINDER1 = List.of("0","18","8","9","10","11","12","13","14","15","16","17");
    public static final List<Long> TIME_REMINDER2 = List.of(6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L,17L);
    public static final List<Long> TIME_MINUTE = List.of(57L,58L,56L);

    public static String getNameButton(String language, String name){
        Locale locale = new Locale.Builder().setLanguage(language).build();
        ResourceBundle rb = ResourceBundle.getBundle("names", locale);
        return rb.getString(name);

    }
}
