package com.telegrambot.features;

import com.telegrambot.features.telegram.TelegramBotLauncher;
import com.telegrambot.features.telegram.util.Settings;

public class AppLauncher {
    public static void main(String[] args) {
        TelegramBotLauncher launcher = new TelegramBotLauncher();
        System.out.println("Settings.getDefault() = " + Settings.getDefault());

    }
}
