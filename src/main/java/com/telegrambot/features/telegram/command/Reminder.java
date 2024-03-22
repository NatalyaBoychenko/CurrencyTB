package com.telegrambot.features.telegram.command;

import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Reminder {
    @SneakyThrows

    public void handleCallbackReminder(CallbackQuery callbackQuery, Settings settings, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        switch (answer){
            case "9":
                settings.setReminderTime(9);
                System.out.println("successful 9");
                break;
            case "10":
                settings.setReminderTime(10);
                System.out.println("successful 10");
                break;
            case "11":
                settings.setReminderTime(11);
                break;
            case "12":
                settings.setReminderTime(12);
                break;
            case "13":
                settings.setReminderTime(13);
                break;
            case "14":
                settings.setReminderTime(14);
                break;
            case "15":
                settings.setReminderTime(15);
                break;
            case "16":
                settings.setReminderTime(16);
                break;
            case "17":
                settings.setReminderTime(17);
                break;
            case "18":
                settings.setReminderTime(18);
                break;
            default:
                settings.setReminderTime(0);
                break;
        }
        InlineKeyboardMarkup updatedMarkup = Keyboard.setReminderKeyboard(answer);

        EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(updatedMarkup)
                .build();

        bot.execute(editMessageReplyMarkup);
    }
}
