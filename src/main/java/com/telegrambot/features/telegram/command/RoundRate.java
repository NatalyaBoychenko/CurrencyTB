package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class RoundRate {
    @SneakyThrows
    public void handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings,
                                        StorageInMemoryRepo storageInMemory, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        switch (answer) {
            case "3" -> {
                settings.setRoundDigit(3);
                storageInMemory.addSetting(settings.getChatId(), settings);
                System.out.println("successful roundRate 2");
            }
            case "4" -> {
                settings.setRoundDigit(4);
                storageInMemory.addSetting(settings.getChatId(), settings);
                System.out.println("successful roundRate 3");
            }
            default -> {
                settings.setRoundDigit(2);
                storageInMemory.addSetting(settings.getChatId(), settings);
            }
        }

        InlineKeyboardMarkup updatedMarkup = Keyboard.setRoundRateKeyboard(answer);

        EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(updatedMarkup)
                .build();

        bot.execute(editMessageReplyMarkup);
    }
}
