package com.telegrambot.features.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotLauncher {
    private final CurrencyTelegramBot currencyTelegramBot;
    public TelegramBotLauncher() {
        currencyTelegramBot = new CurrencyTelegramBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(currencyTelegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace(); 
        }
    }
}
