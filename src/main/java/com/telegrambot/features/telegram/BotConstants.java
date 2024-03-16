package com.telegrambot.features.telegram;

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
}
