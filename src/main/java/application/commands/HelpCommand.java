package application.commands;

import core.TelegramCommandListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HelpCommand implements TelegramCommandListener {

    private List<TelegramCommandListener> listenerList;
    private Set<String> stopList;

    @Autowired
    public HelpCommand(List<TelegramCommandListener> listenerList) {
        this.listenerList = listenerList;
        this.stopList = new HashSet<>();
        stopList.addAll(Arrays.asList("/help","/start"));
    }
    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(printCommandList());
        return sendMessage;
    }

    private String printCommandList(){
        return listenerList.stream()
                .filter(telegramCommandListener -> !stopList.contains(telegramCommandListener.getSupportedCommand()))
                .map(TCL -> TCL.getSupportedCommand() + " - " + TCL.getDescription())
                .collect(Collectors.joining("\n"));
    }
    @Override
    public String getSupportedCommand() {
        return "/help";
    }

    @Override
    public String getDescription() {
        return "help command";
    }

}
