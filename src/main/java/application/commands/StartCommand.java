package application.commands;

import core.TelegramCommandListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
public class StartCommand implements TelegramCommandListener {
    private static final String START_MSG = "Greetings I`m SuperBot and I can do many useful" +
            " things \"/help\" to display command list";
    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(START_MSG);
        return sendMessage;
    }

    @Override
    public String getSupportedCommand() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "Greetings";
    }
}
