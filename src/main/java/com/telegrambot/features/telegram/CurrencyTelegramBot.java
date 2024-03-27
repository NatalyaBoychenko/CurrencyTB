package com.telegrambot.features.telegram;

import com.telegrambot.features.settings.ExchangeMessage;
import com.telegrambot.features.settings.Settings;
import com.telegrambot.features.settings.StorageInMemoryRepo;
import com.telegrambot.features.telegram.command.*;
import com.telegrambot.features.telegram.util.BotConstants;
import com.telegrambot.features.telegram.util.Keyboard;
import com.telegrambot.features.telegram.util.TGPictures;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.telegrambot.features.telegram.util.BotConstants.BOT_NAME;
import static com.telegrambot.features.telegram.util.BotConstants.BOT_TOKEN;

public class CurrencyTelegramBot extends TelegramLongPollingBot {
    private final RoundRate roundRate;
    private final BankSetting bankSetting;
    private final Reminder reminder;
    private final CurrencySetting currency;
    private StorageInMemoryRepo storageInMemory;
    private Language language;

    private Map<Long, String> users = new HashMap<>();
    private boolean flag1 = true;


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
                    storageInMemory.getSettingForConcreteUser(chatId) : Settings.getDefaultSettings(chatId, storageInMemory);

            handleCallback(update.getCallbackQuery(), settings,storageInMemory, sendMessage);

            if (BotConstants.TIME_REMINDER1.contains(update.getCallbackQuery().getData())) {
                users.put(chatId, update.getCallbackQuery().getData());
            }

            methodReminderTime(settings);

        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    private void methodReminderTime(Settings settings) {
        if (flag1) {
            ScheduledExecutorService executorServiceGeneral = Executors.newSingleThreadScheduledExecutor();

            executorServiceGeneral.scheduleAtFixedRate(() -> {
                        long hourSerGen = LocalTime.now().getHour();
                        long minutesSerGen = LocalTime.now().getMinute();
                        long secondsSerGen = LocalTime.now().getSecond();

                        if (BotConstants.TIME_REMINDER2.contains(hourSerGen)) {

                            if (BotConstants.TIME_MINUTE.contains(minutesSerGen)) {

                                for (Long key : users.keySet()) {
                                    long timeReminder = Long.parseLong(users.get(key));
                                    long start = 0;

                                    if ((timeReminder != 0) && (timeReminder == hourSerGen + 1)) {

                                        start = (59 - minutesSerGen) * 60 + 60 - secondsSerGen;

                                        if (start != 0) {

                                            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                                            executorService.schedule(() -> {

                                                TGPictures.sendImage(settings.getChatId(), "reminder", this);
                                                SendMessage answer = new SendMessage();
                                                answer.setText(ExchangeMessage.printMessage(settings));
                                                answer.setChatId(key);
                                                try {
                                                    execute(answer);
                                                } catch (TelegramApiException e) {
                                                    throw new RuntimeException(e);
                                                }
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

        switch (callBackMessage) {
            case "englLang" -> {
                settings.setLanguage("eng");
                storageInMemory.addSetting(chatId, settings);
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton("eng", "MESSAGE"));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);
            }
            case "ukrLang" -> {
                settings.setLanguage("ukr");
                storageInMemory.addSetting(chatId, settings);
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton("ukr", "MESSAGE"));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);
            }
            case "INFO" -> {
                TGPictures.sendImage(chatId, "info", this);
                sendMessage.setChatId(chatId);
                sendMessage.setText(ExchangeMessage.printMessage(settings));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);
            }
            case "SETTINGS", "BACK" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "SETTINGS"));
                sendMessage.setReplyMarkup(Keyboard.setSettingsKeyboard(settings));
                execute(sendMessage);
            }
            case "HOME" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "MESSAGE"));
                sendMessage.setReplyMarkup(Keyboard.setStartKeyboard(settings));
                execute(sendMessage);
            }
            case "ROUNDED_INDEX" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "ROUNDED_INDEX"));
                sendMessage.setReplyMarkup(Keyboard.setRoundRateKeyboard(settings));
                execute(sendMessage);
            }
            case "BANK" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "BANK") + "                            .");
                sendMessage.setReplyMarkup(Keyboard.setBankKeyboard(settings));
                execute(sendMessage);
            }
            case "CURRENCY" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "CURRENCY") + "                      .");
                sendMessage.setReplyMarkup(Keyboard.setCurrencyKeyboard(settings));
                execute(sendMessage);
            }
            case "REMINDER_TIME" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "REMINDER_TIME"));
                sendMessage.setReplyMarkup(Keyboard.setReminderKeyboard(settings));
                execute(sendMessage);
            }
            case "LANGUAGE" -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotConstants.getNameButton(settings.getLanguage(), "LANGUAGE") + "                    .");
                sendMessage.setReplyMarkup(Keyboard.setLanguageKeyboard(settings));
                execute(sendMessage);
            }
            case "2", "3", "4" -> execute(roundRate.handleCallbackRoundRate(callbackQuery, settings));
            case "privat", "mono", "nbu" ->
                    execute(bankSetting.handleCallbackRoundRate(callbackQuery, settings));
            case "USD", "EUR" -> execute(currency.handleCallbackCurrency(callbackQuery, settings));
            case "eng", "ukr" -> execute(language.handleCallbackLanguage(callbackQuery, settings));
            default -> execute(reminder.handleCallbackReminder(callbackQuery, settings));
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
                    TGPictures.sendImage(message.getChatId(), "greeting", this);
                    execute(SendMessage.builder()
                            .text("""
                                    Curency Telegram Bot

                                    Ласкаво просимо! Цей бот допоможе знайти актуальний курс 
                                    валют. Для початку оберіть мову.

                                    Welcome! This bot will help you find current exchange rate.
                                    Choose a language for start.

                                    """)
                            .chatId(message.getChatId().toString())
                            .replyMarkup(Keyboard.setStartLanguageKeyboard())
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
        System.out.println("Message from " + firstName + " " + lastName + ", username " + username + ". (id = " + userId + ")\nText - " + txt);
    }
}





