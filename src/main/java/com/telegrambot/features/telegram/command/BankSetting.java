package com.telegrambot.features.telegram.command;

import com.telegrambot.features.currency.PrivatBankCurrencyService;
import com.telegrambot.features.telegram.CurrencyTelegramBot;
import com.telegrambot.features.telegram.Settings;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static com.telegrambot.features.telegram.util.BotConstants.BANK;

public class BankSetting {
    @SneakyThrows
        public void handleCallbackRoundRate(CallbackQuery callbackQuery, Settings settings, CurrencyTelegramBot bot) {
            String answer = callbackQuery.getData();
            Long chatId = callbackQuery.getMessage().getChatId();
            Integer messageId = callbackQuery.getMessage().getMessageId();

        switch (answer){
            case "mono":
                //settings.setBank(new MonoBankCurrencyService());
                System.out.println("successful");
                break;
            case "nbu":
                //settings.setBank(new NBUCurrencyService());
                System.out.println("successful");
                break;
            default:
                settings.setBank(new PrivatBankCurrencyService());
                System.out.println("successful");
                break;
        }
            InlineKeyboardMarkup updatedMarkup = Keyboard.setBankKeyboard(answer);

            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .replyMarkup(updatedMarkup)
                    .build();

            bot.execute(editMessageReplyMarkup);
    }
}
