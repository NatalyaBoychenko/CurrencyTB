package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class RoundRate {

    public EditMessageReplyMarkup handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory) {
        String answer = callbackQuery.getData();
        Message message = (Message) callbackQuery.getMessage();

        switch (answer){
            case "3" -> {
                settings.setRoundDigit(3);
            }
            case "4"-> {
                settings.setRoundDigit(4);
            }
            default -> {
                settings.setRoundDigit(2);
            }

        }

        return EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getRoundRateButtons(settings))
                        .build())
                .build();

    }


}
