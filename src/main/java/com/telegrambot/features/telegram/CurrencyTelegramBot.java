package com.telegrambot.features.telegram;

import com.telegrambot.features.telegram.command.BankSetting;
import com.telegrambot.features.telegram.command.CurrencySetting;
import com.telegrambot.features.telegram.command.Reminder;
import com.telegrambot.features.telegram.command.RoundRate;
import com.telegrambot.features.telegram.util.Keyboard;
import com.telegrambot.features.telegram.util.SavedSettings;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.Optional;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;
import static com.telegrambot.features.telegram.util.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    private final RoundRate roundRate;
    private final BankSetting bankSetting;
    private final Reminder reminder;
    private final CurrencySetting currency;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        roundRate = new RoundRate();
        bankSetting = new BankSetting();
        reminder = new Reminder();
        currency = new CurrencySetting();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Settings settingForUser;
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        Settings settingForUser;
        String callBackMessage = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!SavedSettings.isUserSettingsPresent(chatId)) {
            settingForUser = new Settings(chatId);
            SavedSettings.addSetting(chatId, settingForUser);

        } else {
            settingForUser = SavedSettings.getSettingForUser(chatId);
        }

        if (callBackMessage.equals(INFO)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(Settings.getDefault());
//                sendMessage.setText(Settings.getDefaultSettings(chatId));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard());
                execute(sendMessage);

            } else if (callBackMessage.equals(SETTINGS)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(SETTINGS);
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
                execute(sendMessage);

            } else if (callBackMessage.equals(HOME)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(Settings.getDefault());
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard());
                execute(sendMessage);

            } else if (callBackMessage.equals(ROUNDED_INDEX)) {
                roundRate.handleCallbackRoundRate(callbackQuery, settingForUser, this);

            } else if (callBackMessage.equals(BANK)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settingForUser, this);

            } else if (callBackMessage.equals(CURRENCY)) {
                currency.handleCallbackCurrency(callbackQuery, settingForUser, this);

            } else if (callBackMessage.equals(REMINDER_TIME)) {
                reminder.handleCallbackReminder(callbackQuery, settingForUser, this);

            } else if (callBackMessage.equals(BACK)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(SETTINGS);
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
                execute(sendMessage);

            } else if (callBackMessage.equals("2") || callBackMessage.equals("3") || callBackMessage.equals("4")) {
                roundRate.handleCallbackRoundRate(callbackQuery, settingForUser, this);
            } else if (callBackMessage.equals("9") || callBackMessage.equals("10") || callBackMessage.equals("11") ||
                callBackMessage.equals("12") || callBackMessage.equals("13") || callBackMessage.equals("14") ||
                callBackMessage.equals("15") || callBackMessage.equals("16") || callBackMessage.equals("17") ||
                callBackMessage.equals("18")) {
                reminder.handleCallbackReminder(callbackQuery, settingForUser, this);
            } else if (callBackMessage.equals(PRIVAT_BANK) || callBackMessage.equals(MONOBANK) || callBackMessage.equals(NBU)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settingForUser, this);
            } else if (callBackMessage.equals(USD.name()) || callBackMessage.equals(EUR.name())) {
                currency.handleCallbackCurrency(callbackQuery, settingForUser, this);
            }
        System.out.println(SavedSettings.getSettingForUser(chatId));
//                System.out.println("the end");

    }
    @SneakyThrows
    private void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText();
                if (command.equals("/start")) {
                    execute(SendMessage.builder()
                            .text("Ласкаво просимо. Цей бот допоможе отримати актуальний курс валют")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(Keyboard.setStartKeyboard())
                            .build());
                }
            }
        }
    }
}





