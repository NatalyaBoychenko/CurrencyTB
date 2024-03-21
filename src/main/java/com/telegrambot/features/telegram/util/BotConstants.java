package com.telegrambot.features.telegram.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class BotConstants {
    public static final String BOT_NAME = "name";
    public static final String BOT_TOKEN = "token";
    public static final String  INFO = new String("Отримати інфо".getBytes(), StandardCharsets.UTF_8);
    public static final String SETTINGS = new String("Налаштування".getBytes(), StandardCharsets.UTF_8);
    public static final String  ROUNDED_INDEX = new String("Кількість знаків після коми".getBytes(), StandardCharsets.UTF_8);
    public static final String  BANK = new String("Банк".getBytes(), StandardCharsets.UTF_8);
    public static final String  CURRENCY = new String("Валюта".getBytes(), StandardCharsets.UTF_8);
    public static final String  REMINDER_TIME = new String("Час сповіщення".getBytes(), StandardCharsets.UTF_8);
    public static final String HOME = new String("На головну".getBytes(), StandardCharsets.UTF_8);
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    public static final String  BACK = new String( "Назад".getBytes(), StandardCharsets.UTF_8);
    public static final String CANCEL_REMINDER = new String("Вимкнути сповіщення".getBytes(), StandardCharsets.UTF_8);
//    public static final String  START = "START";
    public static final String NBU = new String("НБУ".getBytes(), StandardCharsets.UTF_8);
    public static final String MONOBANK = new String("МоноБанк".getBytes(), StandardCharsets.UTF_8);
    public static final String PRIVAT_BANK = new String("ПриватБанк".getBytes(), StandardCharsets.UTF_8);
}
