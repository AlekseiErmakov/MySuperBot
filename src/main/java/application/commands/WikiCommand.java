package application.commands;

import core.TelegramCommandListener;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Service
public class WikiCommand implements TelegramCommandListener {
    private static final String BASE_URL = "https://ru.wikipedia.org/wiki/";
    private static final String UNSUPPORTED = " I don`t know such word!";
    private static final String MAIN_TAG = "p";

    @Override
    public BotApiMethod<Message> onCommandReceived(Update update) {
        Message message = update.getMessage();
        SendMessage answer = getAnswer(message);
        return answer;
    }

    private SendMessage getAnswer(Message message) {
        String text = message.getText();
        String request = text.substring(text.indexOf(" ") + 1);
        String answer = answerFromWiki(request);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answer);
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }

    private String answerFromWiki(String userRequest) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + userRequest)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                String string = body.string();

                return parseRequest(string);
            } else {
                return userRequest + UNSUPPORTED;
            }

        } catch (IOException e) {
            return userRequest + UNSUPPORTED;
        }

    }

    private String parseRequest(String string) {
        Document document = Jsoup.parse(String.valueOf(string));
        Elements elementsByTag = document.getElementsByTag(MAIN_TAG);
        String text = elementsByTag.get(0).text();
        return text;
    }

    @Override
    public String getSupportedCommand() {
        return "/wiki";
    }

    @Override
    public String getDescription() {
        return "plus any word and it will display it`s meaning";
    }
}
