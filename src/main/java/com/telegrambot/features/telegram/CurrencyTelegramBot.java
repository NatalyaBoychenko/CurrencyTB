package com.telegrambot.features.telegram;

import com.telegrambot.features.telegram.util.Keyboard;
import com.telegrambot.features.telegram.util.Settings;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.telegrambot.features.telegram.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
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
            execute(sendMessage);
        } else if (callBackMessage.equals(SETTINGS)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Settings");
            sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
            execute(sendMessage);
        }


    }
    @SneakyThrows
    private void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText();
                switch (command) {
                    case "/start":
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




