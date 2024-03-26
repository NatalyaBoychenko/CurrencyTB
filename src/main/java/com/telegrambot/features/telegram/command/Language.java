package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Language {
    @SneakyThrows
    public EditMessageReplyMarkup handleCallbackLanguage(CallbackQuery callbackQuery, Settings settings) {

        String answer = callbackQuery.getData();
        Integer messageId = callbackQuery.getMessage().getMessageId();


        if (answer.equals("ukr")) {
            settings.setLanguage("ukr");
        } else {
            settings.setLanguage("eng");
        }


        return EditMessageReplyMarkup.builder()
                .chatId(settings.getChatId())
                .messageId(messageId)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getLanguageButtons(settings))
                        .build())
                .build();
    }


}
