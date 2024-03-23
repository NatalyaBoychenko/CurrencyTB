package com.telegrambot.features.telegram;

import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.ExchangeMessage;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.command.*;
import com.telegrambot.features.telegram.util.BotConstants;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

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
    private Language language;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        storageInMemory = new StorageInMemoryRepo();
        roundRate = new RoundRate();
        bankSetting = new BankSetting();
        language = new Language();
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

        if (callBackMessage.equals("englLang")){
           settings.setLanguage("eng");
           storageInMemory.addSetting(chatId, settings);
            sendMessage.setChatId(chatId);
//            String text = BotConstants.getEng().get("MESSSAGE");
            sendMessage.setText(BotConstants.getNameButton("eng", "MESSAGE"));
            sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));

            execute(sendMessage);
        } else  if (callBackMessage.equals("ukrLang")){
            settings.setLanguage("ukr");
            storageInMemory.addSetting(chatId, settings);
            sendMessage.setChatId(chatId);
//            String text = BotConstants.getUkr().get("MESSSAGE");
            sendMessage.setText(BotConstants.getNameButton("ukr", "MESSAGE"));
            sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
            execute(sendMessage);
        } else if (callBackMessage.equals(INFO)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(ExchangeMessage.printMessage(settings));
//                sendMessage.setText(Settings.getDefaultSettings(chatId));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);

            } else if (callBackMessage.equals(SETTINGS) || callBackMessage.equals(BACK)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "SETTINGS"));
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard(settings));
                execute(sendMessage);

            } else if (callBackMessage.equals(HOME)) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "MESSAGE"));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);

            } else if (callBackMessage.equals(ROUNDED_INDEX)) {
                roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory , this);

            } else if (callBackMessage.equals(BANK)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);

            } else if (callBackMessage.equals(CURRENCY)) {
                currency.handleCallbackCurrency(callbackQuery, settings, storageInMemory, this);

            } else if (callBackMessage.equals(REMINDER_TIME)) {
                reminder.handleCallbackReminder(callbackQuery, settings, storageInMemory, this);
            } else if (callBackMessage.equals("LANGUAGE")) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "MESSAGE"));
                sendMessage.setReplyMarkup(Keyboard.setLanguageKeyboard(settings));
                execute(sendMessage);


//            } else if (callBackMessage.equals(BACK)) {
//                sendMessage.setChatId(chatId);
//                sendMessage.setText(SETTINGS);
//                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard(settings));
//                execute(sendMessage);

            } else if (callBackMessage.equals("2") || callBackMessage.equals("3") || callBackMessage.equals("4")) {
                roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);
            } else if (callBackMessage.equals(PRIVAT_BANK) || callBackMessage.equals(MONOBANK) || callBackMessage.equals(NBU)) {
                bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory,this);
            } else if (callBackMessage.equals(USD.name()) || callBackMessage.equals(EUR.name())) {
                currency.handleCallbackCurrency(callbackQuery, settings,storageInMemory, this);
            } else if (callBackMessage.equals("eng") || callBackMessage.equals("ukr")) {
                language.handleCallbackLanguage(callbackQuery, settings,storageInMemory, this);
            } else
//                if (callBackMessage.equals("9") || callBackMessage.equals("10") ||
//                callBackMessage.equals("11") || callBackMessage.equals("12") ||
//                callBackMessage.equals("13") || callBackMessage.equals("14") ||
//                callBackMessage.equals("15") || callBackMessage.equals("16") ||
//                callBackMessage.equals("17") || callBackMessage.equals("18"))
                {
            reminder.handleCallbackReminder(callbackQuery, settings, storageInMemory,this);
        }


    }
    @SneakyThrows
    private void handleMessage(org.telegram.telegrambots.meta.api.objects.Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText();
                if (command.equals("/start")) {

                    execute(SendMessage.builder()
                            .text("""
                                    Curency Telegram Bot

                                    Ласкаво просимо! Цей бот допоможе знайти актуальний курс валют. Для початку оберыть мову.

                                    Wellcome! This bot helps you find actual currency info. Choose language for start

                                    """)
                            .chatId(message.getChatId().toString())
                            .replyMarkup(Keyboard.setStartLanguageKeyboard())
                            .build());

//                    execute(SendMessage.builder()
//                            .text("Ласкаво просимо. Цей бот допоможе отримати актуальний курс валют")
//                            .chatId(message.getChatId().toString())
//                            .replyMarkup(Keyboard.setStartKeyboard())
//                            .build());
                }
            }
        }
    }
}





