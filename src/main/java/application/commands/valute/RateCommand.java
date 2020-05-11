package application.commands.valute;

import core.TelegramCommandListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RateCommand implements TelegramCommandListener {
    private ExchangeRateService rateService;
    private static final String ALERT_MSG = "Please add currency name after command";
    private static final String UNKNOWN_CURRENCY = "I can`t find currency with name \"%s\" maybe you fault or currency does not exist";

    @Autowired
    public RateCommand(ExchangeRateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(processRequest(text));
        return sendMessage;
    }

    private String processRequest(String text) {
        if (!isValidRequest(text)){
            return ALERT_MSG;
        }
        String valute = text.substring(getSupportedCommand().length()+1);
        try{
            rateService.updateCourses();
        } catch (ServerUnavailableException ex){
            return ex.getMessage();
        }

        if (!rateService.isPresent(valute)){
            return String.format(UNKNOWN_CURRENCY,valute);
        }
        return rateService.getExchangeRate(valute);
    }

    private boolean isValidRequest(String request){
        return request.split(" ").length >= 2;
    }

    @Override
    public String getSupportedCommand() {
        return "/rate";
    }

    @Override
    public String getDescription() {
        return "plus currency name for current exchange rate";
    }
}
