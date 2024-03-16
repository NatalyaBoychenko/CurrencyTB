package com.telegrambot.features.telegram;

import com.telegrambot.features.telegram.command.SettingsMenu;
import com.telegrambot.features.telegram.command.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.StandardCharsets;

import static com.telegrambot.features.telegram.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    SettingsMenu settingsMenu = new SettingsMenu();

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

        SendMessage responseMessage = new SendMessage();

        if (update.hasCallbackQuery()) {

            String callbackQuery = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();


            if (callbackQuery.equals(SETTINGS)) {

                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Налаштування".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(settingsMenu.setKeyboard());

            } else if (callbackQuery.equals(HOME)) {
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Виберіть одну із дій".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(settingsMenu.standardKeyboard());
            }
        }

        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
