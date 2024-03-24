package com.telegrambot.features.telegram.util;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.File;

public class TGPictures extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return "YourBotToken";
    }

    public static void sendImage(Long chatId, String name) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File("Pictures" + name + ".jpg")));

        TGPictures bot = new TGPictures();
        try {
            bot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
