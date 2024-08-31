package com.telegrambot.features.settings;

import com.telegrambot.features.currency.dto.Currency;
import com.telegrambot.features.telegram.util.BotConstants;
import com.telegrambot.features.telegram.util.Icon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.telegrambot.features.telegram.util.BotConstants.getNameButton;

public class ExchangeMessage {
    public static String printMessage(Settings settings) {

        StringBuilder result = new StringBuilder();
        String courseIn = BotConstants.getNameButton(settings.getLanguage(), "COURCE_IN");
        String buy = BotConstants.getNameButton(settings.getLanguage(), "BUY");
        String sell = BotConstants.getNameButton(settings.getLanguage(), "SELL");

        String bankName = getBankButton(settings, settings.getBank().getName());

        for (int i = 0; i < settings.getCurrencies().size(); i++) {

            result.append(courseIn)
                    .append(bankName).append("                                                                          \n")
                    .append(settings.getCurrencies().get(i)).append("/UAN")
                    .append("\n")
                    .append(buy).append(getRoundedBuyRate(settings).get(i))
                    .append("\n")
                    .append(sell).append(getRoundedSellRate(settings).get(i))
                    .append("\n\n");
        }

        return result.toString();
    }

    private static String getBankButton(Settings settings, String bankName){
        String name = "";
        switch (bankName){
            case "PrivatBank" -> name = getNameButton(settings.getLanguage(), "PRIVAT_BANK");
            case "MonoBank" -> name = getNameButton(settings.getLanguage(), "MONOBANK");
            case "NBU" -> name = getNameButton(settings.getLanguage(), "NBU");
            default -> System.out.println();

        }

        return name;
    }

    private static List<BigDecimal> getRoundedBuyRate(Settings settings) {

        List<BigDecimal> roundedRate = new ArrayList<>();
        BigDecimal roundedBuy = new BigDecimal(0);

        for (Currency currency : settings.getCurrencies()) {

            roundedBuy = BigDecimal.valueOf(
                            settings.getBank().getBuyRate(currency))
                    .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);
            roundedRate.add(roundedBuy);
        }

        return roundedRate;

    }

    private static List<BigDecimal> getRoundedSellRate(Settings settings) {

        List<BigDecimal> roundedRate = new ArrayList<>();
        BigDecimal roundedSell = new BigDecimal(0);

        for (Currency currency : settings.getCurrencies()) {

            roundedSell = BigDecimal.valueOf(
                            settings.getBank().getSellRate(currency))
                    .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);
            roundedRate.add(roundedSell);
        }

        return roundedRate;

    }

    private static String getButton(Settings settings, String language, String name){

        return settings.getLanguage().equals(language) ? (Icon.CHECK.get() + name) : name;
    }

}
