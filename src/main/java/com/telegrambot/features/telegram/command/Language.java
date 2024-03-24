package com.telegrambot.features.telegram.command;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Language {
    @SneakyThrows
    public EditMessageReplyMarkup handleCallbackLanguage(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory) {

        String answer = callbackQuery.getData();
        Integer messageId = callbackQuery.getMessage().getMessageId();


        if (answer.equals("ukr")) {

            //settings.setBank(new MonoBankCurrencyService());
            settings.setLanguage("ukr");
            storageInMemory.addSetting(settings.getChatId(), settings);
            System.out.println("successful");
        } else if (answer.equals("eng")) {
            //settings.setBank(new NBUCurrencyService());
            settings.setLanguage("eng");
            storageInMemory.addSetting(settings.getChatId(), settings);
            System.out.println("successful");
        }else  {

            System.out.println("successful");
        }



        return EditMessageReplyMarkup.builder()
                .chatId(settings.getChatId())
                .messageId(messageId)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getLanguageButtons(settings))
                        .build())
                .build();


    }

//    @SneakyThrows
//    public void handleDefaultCallbackLanguage(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory) {
//
//        String answer = callbackQuery.getData();
//        Integer messageId = callbackQuery.getMessage().getMessageId();
//
//
//        if (answer.equals("ukr") || answer.equals("ukrainian")) {
//
//            //settings.setBank(new MonoBankCurrencyService());
//            settings.setLanguage("ukr");
//            storageInMemory.addSetting(settings.getChatId(), settings);
//            System.out.println("successful");
//        } else if (answer.equals("eng") || answer.equals("english")) {
//            //settings.setBank(new NBUCurrencyService());
//            settings.setLanguage("eng");
//            storageInMemory.addSetting(settings.getChatId(), settings);
//            System.out.println("successful");
//        }else  {
//
//            System.out.println("successful");
//        }
//
//    }
}
