package application.commands;

import application.scripts.cities.CitiesGame;
import core.TelegramBotConfig;
import core.TelegramCommandListener;
import core.TelegramLibBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
public class CityGameCommand implements TelegramCommandListener {


    private CitiesGame citiesGame;
    private static final String STARTMESSAGE = "Hello let`s play cities";

    @Autowired
    public CityGameCommand(CitiesGame citiesGame) {
        this.citiesGame = citiesGame;
    }


    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        activateScript();
        SendMessage startMessage = getStartMessage(update);
        return startMessage;
    }

    private SendMessage getStartMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        Message fromUser = update.getMessage();
        sendMessage.setText(STARTMESSAGE);
        sendMessage.setChatId(fromUser.getChatId());
        return sendMessage;
    }

    private void activateScript() {
        TelegramBotConfig config = TelegramBotConfig.getInstance();
        config.setActiveScript(true);
        config.setActiveProvider(citiesGame);
    }

    @Override
    public String getSupportedCommand() {
        return "/cities";
    }

    @Override
    public String getDescription() {
        return "to play game";
    }

}