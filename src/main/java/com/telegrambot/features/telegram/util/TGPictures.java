import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            // Отправка первого изображения
            SendPhoto msg1 = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto("URL_изображения_1")
                    .setCaption("Это лого");
            try {
                execute(msg1);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            // Отправка второго изображения
            SendPhoto msg2 = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto("URL_изображения_2")
                    .setCaption("Это напоминание");
            try {
                execute(msg2);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "Имя бота";
    }

    @Override
    public String getBotToken() {
        return "Токен бота";
    }
}