package application.scripts.cities;

import core.ScriptProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

@Service
public class CitiesGame implements ScriptProvider {

    private Set<String> cityNames = new HashSet<>();

    private CityService cityService;
    private CityContainer cityContainer;

    private static final String LOOSE_MSG = " I can`t found any city i loose!";
    private static final String CITY_IS_NOT_VALID = "The city named %s is not valid, because %s does not contain %s";
    private static final String CITY_IS_NOT_VALID_START = "The city named %s is not valid, because there are cities starts with \"%s\"";
    private static final String CITY_NOT_FOUND = " I can`t find city with name %s maybe you fault or city does not exist";
    private static final String CITY_EXIST = " The city, named %s was already defined in this scope";


    @Autowired
    public CitiesGame(CityService cityService,CityContainer cityContainer) {
        this.cityService = cityService;
        this.cityContainer = cityContainer;
    }

    @Override
    public BotApiMethod<Message> onRequestReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();
        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(processUserRequest(text));
        return sendMessage;
    }

    @Override
    public void refreshData() {
        cityContainer.clearAllData();
    }

    public String processUserRequest(String userCity){
        String curCity = cityContainer.getCurCity();
        if (cityContainer.containsCityName(userCity)){
            return String.format(CITY_EXIST,userCity);
        }
        if (!cityService.isPresent(userCity)){
            return String.format(CITY_NOT_FOUND,userCity);
        }
        if (!cityContainer.currentCityContainsLetter(userCity)){

            return String.format(CITY_IS_NOT_VALID,userCity,curCity,userCity.substring(0,1));
        }

        if (!cityContainer.isValidCityName(userCity)){
            String actualLetter = cityService.findNextCity(curCity,cityNames).substring(0,1);
            return String.format(CITY_IS_NOT_VALID_START,userCity,actualLetter);
        }
        if (!cityContainer.hasAnswer(userCity)){
            return LOOSE_MSG;
        }

        cityContainer.addCity(userCity);
        String botAnswer = cityContainer.generateCityName(userCity);
        cityContainer.addCity(botAnswer);
        cityContainer.setCurCity(botAnswer);

        return botAnswer;
    }



}
