package com.telegrambot.features.settings;

import com.telegrambot.features.model.Setting;

import java.util.ArrayList;
import java.util.List;

public class SavedCustomerSettings {
    private final List<Setting> chatSettings;

    public SavedCustomerSettings() {
        this.chatSettings = new ArrayList<>();
    }


    public boolean containsSettingsForConcreteUser(long chatId) {
        for (Setting chatSetting : chatSettings){
            if(chatSetting.getChatId() == chatId){
                return true;
            }
        }
        return false;
    }


    public void addSetting(long chatId, Setting chatSetting) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId) {
                chatSettings.set(i, chatSetting);
                return;
            }
        }
        chatSettings.add(chatSetting);
    }


    public Setting getSettingForConcreteUser(long chatId) {
        for (Setting chatSetting : chatSettings) {
            if (chatSetting.getChatId() == chatId) {
                return chatSetting;
            }
        }
        return null;
    }

}
