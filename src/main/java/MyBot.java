import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyBot extends TelegramLongPollingBot {
    private String botUserName;
    private String botToken;
    private String chatId;
    private static final String TelegramAPIPath="https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    Logger logger = Logger.getLogger(MyBot.class.getName());
    FileHandler fileHandler;
    {
        try {
            fileHandler = new FileHandler("app.log", true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MyBot(String botUserName,String botToken,String chatId)
    {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.chatId = chatId;
    }

    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equals("/start")) {
            sendMessageToChannel(update.getMessage());
            logger.info(""+update.getMessage()+"\n");
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Your message is sent to the admins✅\n we will answer you as soon as possible");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }
    private String getChatId()
    {
        return chatId;
    }
    private void sendMessageToChannel(Message message) {
        String urlString = TelegramAPIPath;

        String userMsg = message.getText();
        userMsg = userMsg.replaceAll("\\n","%0D%0A");

        String text = "[username]:\t@"+message.getFrom().getUserName()+
                "%0D%0A[firstname]:\t"+message.getFrom().getFirstName()+
                "%0D%0A[lastname]:\t"+message.getFrom().getLastName()+
                "%0D%0A["+message.getFrom().getFirstName()+message.getFrom().getLastName()+" says]:%0D%0A"+userMsg;

        urlString = String.format(urlString, getBotToken(), getChatId(), text);

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
