package com.telegrambot.features.settings;

import com.telegrambot.features.currency.dto.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ExchangeMessage {
    public static String printMessage(Settings settings) {

        StringBuilder result = new StringBuilder();


        for (int i = 0; i < settings.getCurrencies().size(); i++) {

            result.append("Курс в " + settings.getBank().getName() + "\n");
            result.append(settings.getCurrencies().get(i) + "/UAN");
            result.append("\nПокупка: " + getRoundedBuyRate(settings).get(i));
            result.append("\nПродаж: " + getRoundedSellRate(settings).get(i));
            result.append("\n\n");
        }

        return result.toString();
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

}
