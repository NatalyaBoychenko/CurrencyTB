package com.telegrambot.features.telegram;

import com.telegrambot.features.telegram.command.BankSetting;
import com.telegrambot.features.telegram.command.Reminder;
import com.telegrambot.features.telegram.command.RoundRate;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.telegrambot.features.telegram.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    RoundRate roundRate;
    BankSetting bankSetting;
    private Reminder reminder;
    private Settings settings;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        roundRate = new RoundRate();
        bankSetting = new BankSetting();
        reminder = new Reminder();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());

        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();

        String callBackMessage = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();


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
            sendMessage.setReplyMarkup(Keyboard.setRoundRateKeyboard());
            execute(sendMessage);

        } else if (callBackMessage.equals(BANK)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(BANK);
            sendMessage.setReplyMarkup(Keyboard.setBankKeyboard());
            execute(sendMessage);

        } else if (callBackMessage.equals(CURRENCY)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(CURRENCY);
            sendMessage.setReplyMarkup(Keyboard.setCurrencyKeyboard());
            execute(sendMessage);
        } else if (callBackMessage.equals(REMINDER_TIME )) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(REMINDER_TIME);
            sendMessage.setReplyMarkup(Keyboard.setReminderKeyboard());
            execute(sendMessage);

        } else if (callBackMessage.equals(BACK)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(SETTINGS);
            sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
            execute(sendMessage);

        } else {
            roundRate.handleCallbackRoundRate(callbackQuery);
            bankSetting.handleCallbackRoundRate(callbackQuery);
            reminder.handleCallbackReminder(callbackQuery);
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





