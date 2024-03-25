package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.MonoBankCurrencyService;
import com.telegrambot.features.currency.NBUService;
import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.util.BotConstants;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Map;

import static com.telegrambot.features.telegram.util.BotConstants.*;

public class BankSetting {

    public EditMessageReplyMarkup handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory) {
        String answer = callbackQuery.getData();
        Message message = (Message) callbackQuery.getMessage();
        switch (answer) {
            case "mono" -> {
                settings.setBank(new MonoBankCurrencyService());
            }
            case "nbu" ->
            {
                settings.setBank(new NBUService());
            }
            default -> {
                settings.setBank(new PrivatBankCurrencyService());
            }
        }

        return EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(Keyboard.getBankButtons(settings))
                        .build())
                .build();
    }


}
