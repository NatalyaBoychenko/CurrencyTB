package com.telegrambot.features.telegram.util;

import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.settings.Settings;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.telegrambot.features.currency.dto.Currency.EUR;
import static com.telegrambot.features.currency.dto.Currency.USD;
import static com.telegrambot.features.telegram.util.BotConstants.*;

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

    public static InlineKeyboardMarkup setRoundRateKeyboard(String roundRate) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text(getItemButtonText("2", roundRate))
                        .callbackData("2")
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text(getItemButtonText("3", roundRate))
                        .callbackData("3")
                        .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                        .text(getItemButtonText("4", roundRate))
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

    private String getBankButton(Settings settings, String name){

        return settings.getBank().getName().equals(name) ? (Icon.CHECK.get() + name) : name.toString();
    }

    public static InlineKeyboardMarkup setBankKeyboard(String selectedBank) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(getItemButtonText("ПриватБанк", selectedBank))
                .callbackData(PRIVAT_BANK)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(getItemButtonText("МоноБанк", selectedBank))
                .callbackData(MONOBANK)
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(getItemButtonText("НБУ", selectedBank))
                .callbackData(NBU)
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

    private String getCurrencyButton(Settings settings, String name){

        return settings.getCurrencies().contains(Currency.valueOf(name)) ?
                (Icon.CHECK.get() + name) : name;

    }


    public static InlineKeyboardMarkup setCurrencyKeyboard(Settings settings) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(getCurrencyButton(settings, USD.name()))
                .callbackData(USD.name())
                .build()));
        buttons.add(Collections.singletonList(InlineKeyboardButton.builder()
                .text(getCurrencyButton(settings, EUR.name()))
                .callbackData(EUR.name())
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

    public static InlineKeyboardMarkup setReminderKeyboard(String reminder) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("9", reminder))
                        .callbackData("9")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("10", reminder))
                        .callbackData("10")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("11", reminder))
                        .callbackData("11")
                        .build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("12", reminder))
                        .callbackData("12")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("13", reminder))
                        .callbackData("13")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("14", reminder))
                        .callbackData("14")
                        .build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("15", reminder))
                        .callbackData("15")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("16", reminder))
                        .callbackData("16")
                        .build(),
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("17", reminder))
                        .callbackData("17")
                        .build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(getItemButtonText("18", reminder))
                        .callbackData("18")
                        .build(),
                InlineKeyboardButton.builder().text(CANCEL_REMINDER).callbackData("0").build()));

        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(BACK).callbackData(BACK).build(),
                InlineKeyboardButton.builder().text(HOME).callbackData(HOME).build()));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    private static String getItemButtonText(String itemName, String selectedItem) {
        return selectedItem.equals(itemName) ? itemName + " ✅" : itemName;
    }
}
