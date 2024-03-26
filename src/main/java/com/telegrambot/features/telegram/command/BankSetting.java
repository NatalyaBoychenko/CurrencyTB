package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.MonoBankCurrencyService;
import com.telegrambot.features.currency.NBUService;
import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BankSetting {

    public EditMessageReplyMarkup handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings) {
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
