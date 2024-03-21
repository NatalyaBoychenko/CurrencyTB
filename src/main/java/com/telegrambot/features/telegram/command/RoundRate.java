package com.telegrambot.features.telegram.command;

import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.telegrambot.features.telegram.util.BotConstants.BANK;
import static com.telegrambot.features.telegram.util.BotConstants.ROUNDED_INDEX;


public class RoundRate {
    @SneakyThrows
    public void handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        if (answer.equals("2")) {
            settings.setRoundDigit(2);
            System.out.println("successful roundRate 2");
        } else if (answer.equals("3")) {
            settings.setRoundDigit(3);
            System.out.println("successful roundRate 3");
        } else if (answer.equals("4")) {
            settings.setRoundDigit(4);
            System.out.println("successful roundRate 4");
        } else {
            System.out.println("successful roundRate");
        }
//        switch (answer){
//            case "2":
//                settings.setRoundDigit(2);
//                System.out.println("successful roundRate 2");
//                break;
//            case "3":
//                settings.setRoundDigit(3);
//                System.out.println("successful roundRate 3");
//                break;
//            case "4":
//                settings.setRoundDigit(4);
//                System.out.println("successful roundRate 4");
//                break;
//            default:
//                System.out.println("successful roundRate");
//        }
        InlineKeyboardMarkup updatedMarkup = Keyboard.setRoundRateKeyboard(answer);

        EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(updatedMarkup)
                .build();

        bot.execute(editMessageReplyMarkup);
    }
}
