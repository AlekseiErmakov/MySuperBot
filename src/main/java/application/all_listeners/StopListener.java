package application.all_listeners;

import core.OnAllUpdatesListener;
import core.TelegramBotConfig;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
public class StopListener implements OnAllUpdatesListener {

    private TelegramBotConfig config = TelegramBotConfig.getInstance();
    private static final String STOP = "/stop";
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            if (message.getText().equals(STOP)){
                config.setActiveScript(false);
            }
        }
    }
}
