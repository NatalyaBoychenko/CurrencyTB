package com.telegrambot.features.telegram.util;

import com.telegrambot.features.telegram.Settings;

import java.util.ArrayList;
import java.util.List;

public class SavedSettings {
    private static final List<Settings> savedSettings = new ArrayList<>();

    public static boolean isUserSettingsPresent(long chatId) {
        for (Settings savedSetting : savedSettings) {
            if (savedSetting.getChatId() == chatId) {
                return true;
            }
        }
        return false;
    }
    public static void addSetting(long chatId, Settings setting) {
        for (int i = 0; i < savedSettings.size(); i++) {
            if (savedSettings.get(i).getChatId() == chatId) {
                savedSettings.set(i, setting);
            }
        }
        savedSettings.add(setting);
    }


    public static Settings getSettingForUser(long chatId) {
        for (Settings savedSetting : savedSettings) {
            if (savedSetting.getChatId() == chatId) {
                return savedSetting;
            }
        }
        return null;
    }

}
