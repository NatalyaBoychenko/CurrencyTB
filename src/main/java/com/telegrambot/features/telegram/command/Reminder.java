package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Reminder {

    public EditMessageReplyMarkup handleCallbackReminder(CallbackQuery callbackQuery, Settings settings) {
        String answer = callbackQuery.getData();
        Message message = (Message) callbackQuery.getMessage();

        switch (answer) {
            case "9" -> {
                settings.setReminderTime(9);
            }
            case "10" -> {
                settings.setReminderTime(10);
            }
            case "11" -> {
                settings.setReminderTime(11);
            }
            case "12" -> {
                settings.setReminderTime(12);
            }
            case "13" -> {
                settings.setReminderTime(13);
            }
            case "14" -> {
                settings.setReminderTime(14);
            }
            case "15" -> {
                settings.setReminderTime(15);
            }
            case "16" -> {
                settings.setReminderTime(16);
            }
            case "17" -> {
                settings.setReminderTime(17);
            }
            case "18" -> {
                settings.setReminderTime(18);
            }
            default ->   {
                settings.setReminderTime(26);
            }
        }

        return EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getReminderButtons(settings))
                        .build())
                .build();
    }



}
