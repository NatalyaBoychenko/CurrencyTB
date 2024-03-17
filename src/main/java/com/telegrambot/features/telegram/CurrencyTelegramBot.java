package com.telegrambot.features.telegram;

import com.telegrambot.features.telegram.command.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.telegrambot.features.telegram.BotConstants.BOT_TOKEN;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        register(new StartCommand());
    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        System.out.println("Non-command");
    }
}
