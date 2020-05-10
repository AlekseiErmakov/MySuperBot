package application.commands;

import core.TelegramCommandListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Random;

@Service
public class PasswordCommand implements TelegramCommandListener {

    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        String answer = getAnswer(message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(answer);
        return sendMessage;
    }

    private String getAnswer(Message message) {
        String text = message.getText();
        String[] allRequest = text.split(" ");
        if (allRequest.length == 2) {
            String num = allRequest[1];
            if (isNumber(num)) {
                return generatePassword(num);
            }
            return allRequest[1] + " should be number from 2 to 10 inclusive!!!";
        }
        return "Request is empty! " + getDescription();
    }

    private String generatePassword(String num) {
        int length = Integer.parseInt(num);
        if (length < 3 || length > 15) {
            return getDescription();
        } else return generatePassword(length);
    }

    private boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Override
    public String getSupportedCommand() {
        return "/pass";
    }

    @Override
    public String getDescription() {
        return "plus number from 3 to 15 and i will generate random password";
    }

    private String generatePassword(int length) {
        StringBuilder sb = new StringBuilder();
        int k;
        char x;
        boolean k1 = false;
        boolean k2 = false;
        boolean k3 = false;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            k = (int) (1 + Math.random() * 3);
            if (k == 1) {
                x = (char) (r.nextInt(26) + 'a');
                sb.append(x);
                k1 = true;
            } else if (k == 2) {
                x = (char) (r.nextInt(26) + 'A');
                sb.append(x);
                k2 = true;
            } else {
                x = (char) (r.nextInt(9) + '0');
                sb.append(x);
                k3 = true;
            }
        }
        if (k1 && k2 && k3) {
            return sb.toString();
        } else {
            return generatePassword(length);
        }
    }
}
