package com.telegrambot.features.telegram;

import com.sun.tools.javac.util.Names;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.ExchangeMessage;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.command.BankSetting;
import com.telegrambot.features.telegram.command.CurrencySetting;
import com.telegrambot.features.telegram.command.Reminder;
import com.telegrambot.features.telegram.command.RoundRate;
import com.telegrambot.features.telegram.util.BotConstants;
import com.telegrambot.features.telegram.util.Keyboard;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;
import static com.telegrambot.features.telegram.util.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    private final RoundRate roundRate;
    private final BankSetting bankSetting;
    private final Reminder reminder;
    private final CurrencySetting currency;
    StorageInMemoryRepo storageInMemory;

    //======================================================
    Map<Long, String> users = new HashMap<>();
    boolean flag1 = true;
    //==============================================

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
        //====================================================================
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        if (BotConstants.TIME_REMINDER1.contains(update.getCallbackQuery().getData())) {
            users.put(chatId, update.getCallbackQuery().getData());
            System.out.println("users = " + users);
        }
//===========================================================================================
        methodReminderTime();


        //===================================================================
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

    private void methodReminderTime() {
        if (flag1) {
            ScheduledExecutorService executorServiceGeneral = Executors.newSingleThreadScheduledExecutor();

            executorServiceGeneral.scheduleAtFixedRate(() -> {
                        long hourSerGen = LocalTime.now().getHour();
                        long minutesSerGen = LocalTime.now().getMinute();
                        long secondsSerGen = LocalTime.now().getSecond();

                        System.out.println("General.scheduleAtFixedRate(() -> { Start" + hourSerGen +":"
                                + minutesSerGen +":" + secondsSerGen);
                        System.out.println("users = " + users);

                        System.out.println("BotConstans.TIME_REMINDER2.contains(hourSerGen) = " + BotConstants.TIME_REMINDER2.contains(hourSerGen));

                        if (BotConstants.TIME_REMINDER2.contains(hourSerGen)) {

                            System.out.println("BotConstans.TIME_MINUTE.contains(minutesSerGen) = " + BotConstants.TIME_MINUTE.contains(minutesSerGen));
                            if (BotConstants.TIME_MINUTE.contains(minutesSerGen)) {

                                for (Long key : users.keySet()) {
                                    long timeReminder = Long.parseLong(users.get(key));
                                    long start = 0;

                                    System.out.println("(timeReminder != 0) && (timeReminder == hourSerGen + 1) = " + ((timeReminder != 0) && (timeReminder == hourSerGen + 1)));
                                    if ((timeReminder != 0) && (timeReminder == hourSerGen + 1)) {

                                        start = (59 - minutesSerGen) * 60 + 60 - secondsSerGen;
                                        ;
                                        System.out.println("start = " + start);
                                        if (start != 0) {

                                            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                                            executorService.schedule(() -> {
// ******************************** вставити ментод відпраки повідомлення*********************
                                                SendMessage answer = new SendMessage();
                                                answer.setText("TEXT MASSAGE  :" + LocalTime.now().getHour()
                                                        + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
                                                answer.setChatId(key);
                                                try {
                                                    execute(answer);
                                                } catch (TelegramApiException e) {
                                                    throw new RuntimeException(e);
                                                }
 //***************************************************************************************
                                            }, start, TimeUnit.SECONDS);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    , 0, 3, TimeUnit.MINUTES);
            flag1 = false;
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery, Settings settings, StorageInMemoryRepo storageInMemory, SendMessage sendMessage) {


        String callBackMessage = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();

        if (callBackMessage.equals(INFO)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(ExchangeMessage.printMessage(settings));
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
            sendMessage.setText("Виберіть одну із дій");
            sendMessage.setReplyMarkup(Keyboard.setStartKeyboard());
            execute(sendMessage);

        } else if (callBackMessage.equals(ROUNDED_INDEX)) {
            roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory , this);

        } else if (callBackMessage.equals(BANK)) {
            bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);

        } else if (callBackMessage.equals(CURRENCY)) {
            currency.handleCallbackCurrency(callbackQuery, settings, storageInMemory, this);

        } else if (callBackMessage.equals(REMINDER_TIME)) {
            reminder.handleCallbackReminder(callbackQuery, settings, storageInMemory, this);

        } else if (callBackMessage.equals(BACK)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(SETTINGS);
            sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard());
            execute(sendMessage);

        } else if (callBackMessage.equals("2") || callBackMessage.equals("3") || callBackMessage.equals("4")) {
            roundRate.handleCallbackRoundRate(callbackQuery, settings, storageInMemory, this);
        } else if (callBackMessage.equals(PRIVAT_BANK) || callBackMessage.equals(MONOBANK) || callBackMessage.equals(NBU)) {
            bankSetting.handleCallbackRoundRate(callbackQuery, settings, storageInMemory,this);
        } else if (callBackMessage.equals(USD.name()) || callBackMessage.equals(EUR.name())) {
            currency.handleCallbackCurrency(callbackQuery, settings,storageInMemory, this);
        } else {
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
                            .text("Ласкаво просимо. Цей бот допоможе отримати актуальний курс валют")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(Keyboard.setStartKeyboard())
                            .build());
                }
            }
        }
    }
}