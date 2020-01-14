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
            botsApi.registerBot(new Pwd_obot("pwd_obot","1039225796:AAFemDsfWTSKnI-nmILaz-SEazPqOE2cthk"));
//            botsApi.registerBot(new MyBot("CE97_Adminbot","904554452:AAHBrHUGvIYUiDK-dv1I-ce1AYfPV2hPN9E","-1001466448736"));
//            botsApi.registerBot(new CE97Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
