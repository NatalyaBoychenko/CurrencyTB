package com.telegrambot.features.telegram.command;

import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;

public class Currency {
    @SneakyThrows
    public void handleCallbackCurrency(CallbackQuery callbackQuery, Settings settings, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        switch (answer) {
            case "EUR":
                settings.setCurrencyList(List.of(EUR, USD));
                System.out.println("successful eur");
                break;
            default:
                settings.setCurrencyList(List.of(USD));
                break;
        }
        InlineKeyboardMarkup updatedMarkup = Keyboard.setCurrencyKeyboard(answer);

        EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(updatedMarkup)
                .build();

        bot.execute(editMessageReplyMarkup);
    }

}
