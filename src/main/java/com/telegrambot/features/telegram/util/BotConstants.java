package com.telegrambot.features.telegram.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.List;

@UtilityClass
public class BotConstants {

    public static final String BOT_NAME = "";
    public static final String BOT_TOKEN = "";
    public static final String  INFO = "Отримати інфо";
    public static final String SETTINGS = "Налаштування";
    public static final String  ROUNDED_INDEX = "Кількість знаків після коми";
    public static final String  BANK = "Банк";
    public static final String  CURRENCY = "Валюта";
    public static final String  REMINDER_TIME = "Час сповіщення";
    public static final String HOME = "На головну";
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    public static final String  BACK =  "Назад";
    public static final String CANCEL_REMINDER = "Вимкнути сповіщення";
    //    public static final String  START = "START";
    public static final String NBU = "НБУ";
    public static final String MONOBANK = "МоноБанк";
    public static final String PRIVAT_BANK = "ПриватБанк";
    public static final List<String> TIME_REMINDER1 = List.of("0","18","8","9","10","11","12","13","14","15","16","7");
    public static final List<Long> TIME_REMINDER2 = List.of(6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L,17L);
    public static final List<Long> TIME_MINUTE = List.of(57L,58L,59L);

}