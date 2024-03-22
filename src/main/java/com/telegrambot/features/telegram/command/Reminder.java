package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Reminder {
    @SneakyThrows

    public void handleCallbackReminder(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        switch (answer){
            case "9":
                settings.setReminderTime(9);
                storageInMemory.addSetting(settings.getChatId(), settings);
                System.out.println("successful 9");
                break;
            case "10":
                settings.setReminderTime(10);
                storageInMemory.addSetting(settings.getChatId(), settings);
                System.out.println("successful 10");
                break;
            case "11":
                settings.setReminderTime(11);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "12":
                settings.setReminderTime(12);
                storageInMemory.addSetting(settings.getChatId(), settings);
                System.out.println("12");
                break;
            case "13":
                settings.setReminderTime(13);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "14":
                settings.setReminderTime(14);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "15":
                settings.setReminderTime(15);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "16":
                settings.setReminderTime(16);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "17":
                settings.setReminderTime(17);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            case "18":
                settings.setReminderTime(18);
                storageInMemory.addSetting(settings.getChatId(), settings);
                break;
            default:
                settings.setReminderTime(0);
                storageInMemory.addSetting(settings.getChatId(), settings);
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
