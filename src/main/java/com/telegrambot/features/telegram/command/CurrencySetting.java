package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;

public class CurrencySetting {
    @SneakyThrows
    public void handleCallbackCurrency(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory, CurrencyTelegramBot bot) {
        String answer = callbackQuery.getData();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        List<Currency> currencies = settings.getCurrencies();

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

        InlineKeyboardMarkup updatedMarkup = Keyboard.setCurrencyKeyboard(settings);

        EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                .chatId(settings.getChatId())
                .messageId(messageId)
                .replyMarkup(updatedMarkup)
                .build();

        bot.execute(editMessageReplyMarkup);
    }

}
