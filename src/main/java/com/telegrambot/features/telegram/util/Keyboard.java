package com.telegrambot.features.telegram.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;
import static com.telegrambot.features.telegram.BotConstants.*;
import static com.telegrambot.features.telegram.BotConstants.HOME;
@UtilityClass
public class Keyboard {
    public static InlineKeyboardMarkup setStartKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(INFO)
                .callbackData(INFO)
                .build()));

        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(SETTINGS)
                .callbackData(SETTINGS)
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    public static InlineKeyboardMarkup setSettingsKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(ROUNDED_INDEX)
                .callbackData(ROUNDED_INDEX)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(BANK)
                .callbackData(BANK)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(CURRENCY)
                .callbackData(CURRENCY)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(REMINDER_TIME)
                .callbackData(REMINDER_TIME)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(HOME)
                .callbackData(HOME)
                .build()));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    public static InlineKeyboardMarkup setRoundRateKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text("2")
                        .callbackData("2")
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text("3")
                        .callbackData("3")
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text("4")
                        .callbackData("4")
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text(BACK)
                        .callbackData(BACK)
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(HOME)
                .callbackData(HOME)
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    public static InlineKeyboardMarkup setBankKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(new String("ПриватБанк".getBytes(), StandardCharsets.UTF_8))
                .callbackData("privat")
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(new String("МоноБанк".getBytes(), StandardCharsets.UTF_8))
                .callbackData("mono")
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(new String("НБУ".getBytes(), StandardCharsets.UTF_8))
                .callbackData("nbu")
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(BACK)
                .callbackData(BACK)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(HOME)
                .callbackData(HOME)
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    public static InlineKeyboardMarkup setCurrencyKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(USD.name())
                .callbackData(USD.name())
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(EUR.name())
                .callbackData(EUR.name())
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(new String("НБУ".getBytes(), StandardCharsets.UTF_8))
                .callbackData("nbu")
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(BACK)
                .callbackData(BACK)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(HOME)
                .callbackData(HOME)
                .build()));
        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    public static InlineKeyboardMarkup setReminderKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("9").callbackData("9").build(),
                InlineKeyboardButton.builder().text("10").callbackData("10").build(),
                InlineKeyboardButton.builder().text("11").callbackData("11").build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("12").callbackData("12").build(),
                InlineKeyboardButton.builder().text("13").callbackData("13").build(),
                InlineKeyboardButton.builder().text("14").callbackData("14").build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("15").callbackData("15").build(),
                InlineKeyboardButton.builder().text("16").callbackData("16").build(),
                InlineKeyboardButton.builder().text("17").callbackData("17").build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("18").callbackData("18").build(),
                InlineKeyboardButton.builder().text(CANCEL_REMINDER).callbackData("0").build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(BACK).callbackData(BACK).build(),
                InlineKeyboardButton.builder().text(HOME).callbackData(HOME).build()));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();

    }
}
