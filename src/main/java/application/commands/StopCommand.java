package application.commands;

import core.TelegramBotConfig;
import core.TelegramCommandListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class StopCommand implements TelegramCommandListener {

    private static final String STOP_MESSAGE = "Script stopped";
    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        TelegramBotConfig config = TelegramBotConfig.getInstance();
        config.refreshScriptData();
        config.setActiveScript(false);
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(STOP_MESSAGE);
        return sendMessage;
    }

    @Override
    public String getSupportedCommand() {
        return "/stop";
    }

    @Override
    public String getDescription() {
        return "to stop all process";
    }
}
