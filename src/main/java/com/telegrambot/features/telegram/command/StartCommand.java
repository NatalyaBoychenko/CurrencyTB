package com.telegrambot.features.telegram.command;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.nio.charset.StandardCharsets;

import static com.telegrambot.features.telegram.BotConstants.*;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("Start", "Start bot");
    }

    @Override
    @SneakyThrows
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setText(new String("Ласкаво просимо. Цей бот допоможе отримати актуальний курс валют"
                .getBytes(), StandardCharsets.UTF_8));
        message.setChatId(Long.toString(chat.getId()));

        KeyboardButton infoButton = KeyboardButton.builder().text(INFO).build();
        KeyboardButton setting = KeyboardButton.builder().text(SETTINGS).build();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(infoButton);
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(setting);

        ReplyKeyboardMarkup keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(keyboardRow).keyboardRow(keyboardRow2).build();
        message.setReplyMarkup(keyboard);

        absSender.execute(message);
        System.out.println("successful");
    }
}
