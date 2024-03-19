package com.telegrambot.features.telegram.command;

import com.telegrambot.features.telegram.Settings;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RoundRate {
    Settings settings;

    public void handleCallbackRoundRate(CallbackQuery callbackQuery) {
        settings = new Settings(callbackQuery.getMessage().getChatId());
        String answer = callbackQuery.getData();
        switch (answer){
            case "3":
                settings.setRoundDigit(3);
                System.out.println("input 3");
            case "4":
                settings.setRoundDigit(4);
                System.out.println("input 4");
            default:
                settings.setRoundDigit(2);
        }
    }
}
