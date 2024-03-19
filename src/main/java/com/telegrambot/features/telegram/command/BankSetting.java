package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.telegram.Settings;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BankSetting {
    Settings settings;

        public void handleCallbackRoundRate(CallbackQuery callbackQuery) {
            settings = new Settings(callbackQuery.getMessage().getChatId());
            String answer = callbackQuery.getData();
        switch (answer){
            case "mono":
                //settings.setBank(new MonoBankCurrencyService());
                break;
            case "nbu":
                //settings.setBank(new NBUCurrencyService());
                break;
            default:
                settings.setBank(new PrivatBankCurrencyService());
                break;
        }
    }
}