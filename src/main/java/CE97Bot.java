import org.glassfish.grizzly.http.util.TimeStamp;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class CE97Bot extends TelegramLongPollingBot {
    Logger logger = Logger.getLogger(CE97Bot.class.getName());
    FileHandler fileHandler;
    {
        try {
            fileHandler = new FileHandler("app.log", true);
            logger.addHandler(fileHandler);
//            logger.info();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equals("/start")) {
            sendMessageToChannel(update.getMessage());
            logger.info(""+update.getMessage()+"\n");
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("پیام شما برای ادمین ارسال شد ✅" +
                            "\n"+"در اسرع وقت به پیام شما پاسخ داده خواهد شد.");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    public String getBotUsername() {
        return "CE97_Adminbot";
    }

    @SuppressWarnings("SpellCheckingInspection")
    public String getBotToken() {
        return "904554452:AAHBrHUGvIYUiDK-dv1I-ce1AYfPV2hPN9E";
    }
    private void sendMessageToChannel(Message message) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = getBotToken();
        String chatId = "-1001466448736";
        String userMsg = message.getText();
        userMsg = userMsg.replaceAll("\\n","%0D%0A");
        System.out.println("[2]:"+userMsg);
        String text = "[username]:\t@"+message.getFrom().getUserName()+
                "%0D%0A[firstname]:\t"+message.getFrom().getFirstName()+
                "%0D%0A[lastname]:\t"+message.getFrom().getLastName()+
                "%0D%0A["+message.getFrom().getFirstName()+message.getFrom().getLastName()+" says]:%0D%0A"+userMsg;

        urlString = String.format(urlString, apiToken, chatId, text);

        URL url = null;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Do what you want with response
    }
}