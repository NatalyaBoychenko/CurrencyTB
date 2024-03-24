package com.telegrambot.features.telegram;

import com.telegrambot.features.settings.ExchangeMessage;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.command.BankSetting;
import com.telegrambot.features.telegram.command.CurrencySetting;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;
import static com.telegrambot.features.telegram.util.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    private final RoundRate roundRate;
    private final BankSetting bankSetting;
    private final Reminder reminder;
    private final CurrencySetting currency;
    StorageInMemoryRepo storageInMemory;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        storageInMemory = new StorageInMemoryRepo();
        roundRate = new RoundRate();
        bankSetting = new BankSetting();
        reminder = new Reminder();
        currency = new CurrencySetting();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if (update.hasCallbackQuery()) {

            long chatId = update.getCallbackQuery().getMessage().getChatId();

            Settings settings = storageInMemory.containsSettingsForConcreteUser(chatId) ?
                    storageInMemory.getSettingForConcreteUser(chatId) : Settings.getDefaultSettings(chatId);

            handleCallback(update.getCallbackQuery(), settings,storageInMemory, sendMessage);
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory, SendMessage sendMessage) {
        String callBackMessage = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        sendMessage.setChatId(chatId);

        if (callBackMessage.equals(INFO)) {
                sendMessage.setText(ExchangeMessage.printMessage(settings));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard());

            } else if (callBackMessage.equals(SETTINGS)) {
                sendMessage.setText(SETTINGS);
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());

            } else if (callBackMessage.equals(HOME)) {
                sendMessage.setText("Виберіть одну із дій");
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard());

            } else if (callBackMessage.equals(ROUNDED_INDEX)) {
                roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory , this);

            } else if (callBackMessage.equals(BANK)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);

            } else if (callBackMessage.equals(CURRENCY)) {
                currency.handleCallbackCurrency(callbackQuery, settings, storageInMemory, this);

            } else if (callBackMessage.equals(REMINDER_TIME)) {
                reminder.handleCallbackReminder(callbackQuery, settings, storageInMemory, this);

            } else if (callBackMessage.equals(BACK)) {
                sendMessage.setText(SETTINGS);
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());

            } else if (callBackMessage.equals("2") || callBackMessage.equals("3") || callBackMessage.equals("4")) {
                roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);
            } else if (callBackMessage.equals(PRIVAT_BANK) || callBackMessage.equals(MONOBANK) || callBackMessage.equals(NBU)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory,this);
            } else if (callBackMessage.equals(USD.name()) || callBackMessage.equals(EUR.name())) {
                currency.handleCallbackCurrency(callbackQuery, settings,storageInMemory, this);
            } else {
            reminder.handleCallbackReminder(callbackQuery, settings, storageInMemory,this);
        }
        execute(sendMessage);

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
        if(message.hasText()) {
            String firstName = message.getChat().getFirstName();
            String lastName = message.getChat().getLastName();
            String username = message.getChat().getUserName();
            long userId = message.getChat().getId();
            String messageText = message.getText();

            log(firstName, lastName, username, Long.toString(userId), messageText);
        }
    }

    private void log(String firstName, String lastName, String username, String userId, String txt) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + firstName + " " + lastName + " " + username + " " + ". (id = " + userId + ") \n Text - " + txt);
    }
}





