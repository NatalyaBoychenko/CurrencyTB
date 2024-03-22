package com.telegrambot.features.settings;

import java.util.ArrayList;
import java.util.List;

public class StorageInMemoryRepo {
    private final List<Settings> chatSettings;

    public StorageInMemoryRepo() {
        this.chatSettings = new ArrayList<>();
    }


    public boolean containsSettingsForConcreteUser(long chatId) {
        for (Settings chatSetting : chatSettings){
            if(chatSetting.getChatId() == chatId){
                return true;
            }
        }
        return false;
    }


    public void addSetting(long chatId, Settings chatSetting) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId) {
                chatSettings.set(i, chatSetting);
                return;
            }
        }
        chatSettings.add(chatSetting);
    }


    public Settings getSettingForConcreteUser(long chatId) {
        for (Settings chatSetting : chatSettings) {
            if (chatSetting.getChatId() == chatId) {
                return chatSetting;
            }
        }
        return null;
    }

    public List<Settings> getListOfSettings() {

        return List.copyOf(chatSettings);
    }

}
