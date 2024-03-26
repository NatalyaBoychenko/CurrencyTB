package com.telegrambot.features.settings;

import java.util.HashMap;
import java.util.Map;

public class StorageInMemoryRepo {
    private final Map<Long, Settings> chatSetting;


    public StorageInMemoryRepo() {
        this.chatSetting = new HashMap<>();
    }


    public boolean containsSettingsForConcreteUser(long chatId) {

        return chatSetting.containsKey(chatId);
    }


    public void addSetting(long chatId, Settings settings) {

        chatSetting.put(chatId, settings);
    }


    public Settings getSettingForConcreteUser(long chatId) {

        return chatSetting.get(chatId);
    }

}
