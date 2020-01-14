import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        //Initialize Api Context
        ApiContextInitializer.init();
        //Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new MyBot("myBotUsername","myBotToken","myChatId"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
