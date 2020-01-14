import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Date;

public class Pwd_obot extends MyBot{

    @SuppressWarnings("SpellCheckingInspection")
    public Pwd_obot(String botUserName, String botToken) {
        super(botUserName, botToken,null);
    }

    @Override
    public void onUpdateReceived(Update update) {
//         We check if the update has a message and the message has text
        System.out.println("[Message is here:]"+update.getMessage());
        Date date = new Date();
        if (update.hasMessage() && update.getMessage().hasText()) {
            logger.info(""+update.getMessage()+"\n");
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("[chat id:]"+update.getMessage().getChatId()+"\n[Date:]"+date.getTime()+
                            "\n[username]@"+update.getMessage().getFrom().getUserName()+
                            "\n[firstname]"+update.getMessage().getFrom().getFirstName()+
                            "\n[lastname]"+update.getMessage().getFrom().getLastName()+
                            "\n[language]"+update.getMessage().getFrom().getLanguageCode()+
                            "\n["+update.getMessage().getFrom().getFirstName()+" says:]"+update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
