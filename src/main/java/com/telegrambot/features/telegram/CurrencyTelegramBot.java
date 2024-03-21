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

import static com.telegrambot.features.telegram.util.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    private final RoundRate roundRate;
    private final BankSetting bankSetting;
    private final Reminder reminder;
//    private final SavedSettings savedSettings;
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
//        savedSettings = new SavedSettings();
        currency = new CurrencySetting();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Settings settingForUser;
        if (update.hasCallbackQuery()) {
            if (!SavedSettings.isUserSettingsPresent(update.getUpdateId())) {
                settingForUser = new Settings(update.getUpdateId());
                SavedSettings.addSetting(update.getUpdateId(), settingForUser);
            } else {
                settingForUser = SavedSettings.getSettingForUser(update.getUpdateId());
            }
            handleCallback(update.getCallbackQuery());

            roundRate.handleCallbackRoundRate(update.getCallbackQuery(), settingForUser, this);
            bankSetting.handleCallbackRoundRate(update.getCallbackQuery(), settingForUser, this);
            reminder.handleCallbackReminder(update.getCallbackQuery(), settingForUser, this);
            currency.handleCallbackCurrency(update.getCallbackQuery(), settingForUser, this);
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
//        Settings settingForUser;
        String callBackMessage = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
//        if (!SavedSettings.isUserSettingsPresent(chatId)) {
//            settingForUser = new Settings(chatId);
//            SavedSettings.addSetting(chatId, settingForUser);
//
//        } else {
//            settingForUser = SavedSettings.getSettingForUser(chatId);
//        }
            if (callBackMessage.equals(INFO)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(Settings.getDefault());
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
                sendMessage.setChatId(chatId);
                sendMessage.setText(ROUNDED_INDEX);
                sendMessage.setReplyMarkup(Keyboard.setRoundRateKeyboard(ROUNDED_INDEX));
                execute(sendMessage);

            } else if (callBackMessage.equals(BANK)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BANK);
                sendMessage.setReplyMarkup(Keyboard.setBankKeyboard(BANK));
                execute(sendMessage);

            } else if (callBackMessage.equals(CURRENCY)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(CURRENCY);
                sendMessage.setReplyMarkup(Keyboard.setCurrencyKeyboard(CURRENCY));
                execute(sendMessage);

            } else if (callBackMessage.equals(REMINDER_TIME)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(REMINDER_TIME);
                sendMessage.setReplyMarkup(Keyboard.setReminderKeyboard(REMINDER_TIME));
                execute(sendMessage);

            } else if (callBackMessage.equals(BACK)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(SETTINGS);
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
                execute(sendMessage);

            } else {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Wrong message");
            execute(sendMessage);
//            roundRate.handleCallbackRoundRate(callbackQuery, this);
//            bankSetting.handleCallbackRoundRate(callbackQuery, this);
//            reminder.handleCallbackReminder(callbackQuery, this);
//            currency.handleCallbackCurrency(callbackQuery, this);
//            if (!SavedSettings.isUserSettingsPresent(chatId)) {
//                settingForUser = new Settings(chatId);
//                SavedSettings.addSetting(chatId, settingForUser);
//
//            } else {
//                settingForUser = SavedSettings.getSettingForUser(chatId);
//            }
//                roundRate.handleCallbackRoundRate(callbackQuery, settingForUser, this);
//                bankSetting.handleCallbackRoundRate(callbackQuery, settingForUser, this);
//                reminder.handleCallbackReminder(callbackQuery, settingForUser, this);
//                currency.handleCallbackCurrency(callbackQuery, settingForUser, this);

            }

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





