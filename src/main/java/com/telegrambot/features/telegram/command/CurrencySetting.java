package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;

public class CurrencySetting {

    public EditMessageReplyMarkup handleCallbackCurrency(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory) {
        String answer = callbackQuery.getData();
        List<Currency> currencies = settings.getCurrencies();
        Message message = (Message) callbackQuery.getMessage();
        EditMessageReplyMarkup build = null;

        if (answer.equals(EUR.name())) {
            if (settings.getCurrencies().contains(EUR)) {
                currencies.remove(EUR);
            } else {
                currencies.add(EUR);
            }

        }
        if (answer.equals(USD.name()))
        {
            if (settings.getCurrencies().contains(USD)) {
                currencies.remove(USD);
            } else {
                currencies.add(USD);
            }

        }

        settings.setCurrencies(currencies);
        storageInMemory.addSetting(settings.getChatId(), settings);

        build = EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getCurrencyButtons(settings))
                        .build())
                .build();
        return build;
    }

}
