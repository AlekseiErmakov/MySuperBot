package application.config;

import application.commands.WikiCommand;
import core.OnAllUpdatesListener;
import core.TelegramCommandListener;
import core.TelegramLibBot;
import core.UnsupportedCommandAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;
import java.util.Set;

@Configuration
@ComponentScan("application")
@PropertySource("telegrambot.properties")
public class TelegramConfiguration {
    @Value("${telegram.bot.name}")
    private String botName;
    @Value("${telegram.bot.token}")
    private String botToken;

    private List<TelegramCommandListener> commandListenerList;
    private Set<OnAllUpdatesListener> allUpdatesListeners;
    @Autowired
    public void setCommandListenerList(List<TelegramCommandListener> commandListenerList) {
        this.commandListenerList = commandListenerList;
    }
    @Autowired
    public void setAllUpdatesListeners(Set<OnAllUpdatesListener> allUpdatesListeners) {
        this.allUpdatesListeners = allUpdatesListeners;
    }

    @Bean
    public TelegramLibBot getTelegramLibBot(){
        TelegramLibBot bot = new TelegramLibBot(botName,botToken);
        bot.setListeners(commandListenerList);
        bot.setOnAllUpdatesListener(allUpdatesListeners);
        bot.setUnsupportedCommandAction(UnsupportedCommandAction.COMMANDLIST);
        return bot;
    }

    @Bean
    public TelegramBotsApi getTelegramBotsApi(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(getTelegramLibBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }



}
