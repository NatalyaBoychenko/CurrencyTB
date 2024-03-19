package com.telegrambot.features.telegram.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.telegrambot.features.telegram.BotConstants.*;
import static com.telegrambot.features.telegram.BotConstants.HOME;

//@Data
//@AllArgsConstructor
public class Keyboard {
    public static InlineKeyboardMarkup setStartKeyboard() {
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

    public static InlineKeyboardMarkup setSettingsKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(ROUNDED_INDEX).callbackData(ROUNDED_INDEX).build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(BANK).callbackData(BANK).build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(CURRENCY).callbackData(CURRENCY).build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(REMINDER_TIME).callbackData(REMINDER_TIME).build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(HOME).callbackData(HOME).build()));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }
}
