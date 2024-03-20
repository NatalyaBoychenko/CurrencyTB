package com.telegrambot.features.telegram.command;

import java.util.HashMap;

public class ButtonHandler {
    private final HashMap<String, String> settings;

    public ButtonHandler() {
        this.settings = new HashMap<>();
    }

    public void handleBankCallback(String bankName) {
        // Обробляємо колбек для кнопки банку
        settings.put("bank", bankName);
        System.out.println("Bank setting updated to: " + bankName);
    }

    public void handleCurrencyCallback(String currency) {
        // Обробляємо колбек для кнопки валюти
        settings.put("currency", currency);
        System.out.println("Currency setting updated to: " + currency);
    }

    public void handleRoundCallback(boolean isRounded) {
        // Обробляємо колбек для кнопки округлення
        settings.put("round", String.valueOf(isRounded));
        System.out.println("Rounding setting updated to: " + isRounded);
    }

    public void handleReminderCallback(boolean hasReminder) {
        // Обробляємо колбек для кнопки нагадування
        settings.put("reminder", String.valueOf(hasReminder));
        System.out.println("Reminder setting updated to: " + hasReminder);
    }

    public void printSettings() {
        // Виводимо налаштування користувача
        System.out.println("Current settings:");
        for (String key : settings.keySet()) {
            System.out.println(key + ": " + settings.get(key));
        }
    }

    public static void main(String[] args) {
        ButtonHandler buttonHandler = new ButtonHandler();

        // Приклад виклику методів для обробки колбеків з кнопок
        buttonHandler.handleBankCallback("PrivatBank");
        buttonHandler.handleCurrencyCallback("USD");
        buttonHandler.handleRoundCallback(true);
        buttonHandler.handleReminderCallback(false);

        // Вивід поточних налаштувань
        buttonHandler.printSettings();
    }
}
