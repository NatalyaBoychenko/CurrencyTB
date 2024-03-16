package com.telegrambot.features.telegram.command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.telegrambot.features.telegram.BotConstants.*;

public class SettingsMenu {
    public InlineKeyboardMarkup setKeyboard() {

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(ROUNDED_INDEX).callbackData(ROUNDED_INDEX).build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(BANK).callbackData(BANK).build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(CURRENCY).callbackData(CURRENCY).build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(REMINDER_TIME).callbackData(REMINDER_TIME).build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(HOME).callbackData(HOME).build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;
    }

    public InlineKeyboardMarkup standardKeyboard() {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(InlineKeyboardButton.builder().text(INFO).callbackData(INFO).build());

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        rowInline2.add(InlineKeyboardButton.builder().text(SETTINGS).callbackData(SETTINGS).build());
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

}
