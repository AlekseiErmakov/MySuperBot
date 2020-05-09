package application.commands;

import core.TelegramCommandListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
public class StartCommand implements TelegramCommandListener {
    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Привет Женька");
        return sendMessage;
    }

    @Override
    public String getSupportedCommand() {
        return "/hello";
    }

    @Override
    public String getDescription() {
        return "Greetings";
    }
}
