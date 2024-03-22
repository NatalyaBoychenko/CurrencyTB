package com.telegrambot.features.telegram.util;
import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    CHECK(":white_check_mark:"),
    BACK(":back:"),
    EURO(":euro:"),
    DOLLAR(":dollar:"),
    HOME(":house:");
    private final String value;
    public String get() {
        return EmojiParser.parseToUnicode(value);
    }
    Icon(String value) {
        this.value = value;
    }
}
