package com.telegrambot.features.telegram.util;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class TGPictures extends TelegramLongPollingBot {

    // Путь к изображениям
    private static final String WELCOME_IMAGE_PATH = "C:\\Users\\miros\\OneDrive\\Desktop\\welcome_image.jpg";
    private static final String REMINDER_IMAGE_PATH = "C:\\Users\\miros\\OneDrive\\Desktop\\reminder_image.jpg";

    // Карта для отслеживания пользователей и их статуса первого входа
    private Map<Long, Boolean> firstLoginMap = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return "YourBotToken";
    }

    public void onUpdateReceived(Message message) {
        Long chatId = message.getChatId();
        boolean isFirstLogin = isFirstLogin(chatId);

        if (isFirstLogin) {
            sendImage(chatId, WELCOME_IMAGE_PATH, "Добро пожаловать! Спасибо, что присоединились!");
            firstLoginMap.put(chatId, false); // Установка статуса первого входа в false после отправки приветственного изображения
        } else {
            sendImage(chatId, REMINDER_IMAGE_PATH, "У вас есть важное напоминание!");
            // Другие действия, если это не первый вход пользователя
        }
    }

    // Проверка, является ли это первым входом пользователя
    private boolean isFirstLogin(Long chatId) {
        return !firstLoginMap.containsKey(chatId) || firstLoginMap.get(chatId);
    }

    // Метод для отправки изображения
    private void sendImage(Long chatId, String imagePath, String messageText) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        Path path = Paths.get(imagePath);
        sendPhoto.setPhoto(new InputFile(path.toFile()));

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageText);

        try {
            execute(sendPhoto); // Отправка изображения
            execute(sendMessage); // Отправка сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
