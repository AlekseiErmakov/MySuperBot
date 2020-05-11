package application.commands.valute;

import application.download.DownLoadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeServiceImpl implements ExchangeRateService{
    private ValuteList valuteList;
    private DownLoadService service;
    private static final String BASE_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final String ALERT_MSG = "Server is unavailable!";
    private static final String BASE_MSG = "Курс %s %s на %s составляет %.2f рублей";
    @Autowired
    public ExchangeServiceImpl(DownLoadService service) {
        this.service = service;
    }

    @Override
    public void updateCourses() {
        String apiAnswer = downloadFromCB();
        valuteList = getCurrentRate(apiAnswer);
    }

    private ValuteList getCurrentRate(String apiAnswer) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ValuteList valuteList = mapper.readValue(apiAnswer, ValuteList.class);
            return valuteList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new ServerUnavailableException("Server is unavailable now, please try ones more a bit later");
    }

    private String downloadFromCB() {
        String download = service.download(BASE_URL);
        if (download.isEmpty()){
            return ALERT_MSG;
        }
        return download;
    }

    @Override
    public boolean isPresent(String valute) {
        return valuteList.getValutes()
                .values()
                .stream()
                .anyMatch(currency-> isSubString(valute,currency));
    }

    @Override
    public String getExchangeRate(String valute) {
        List<Valute> valutes = getAllAvailable(valute);
        return valutes.stream()
                .map(currency-> String.format(BASE_MSG,currency.getNominal(),currency.getName(),valuteList.getDate(),currency.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private List<Valute> getAllAvailable(String valute){
        return valuteList.getValutes()
                .values()
                .stream()
                .filter(currency -> isSubString(valute,currency))
                .collect(Collectors.toList());
    }
    private boolean isSubString(String input, Valute currency){

        if (currency.getNominal() == 1){
            return checkSingle(input,currency.getName());
        }
        return checkMulti(input,currency.getName());

    }

    private boolean checkMulti(String input, String fromCB) {
        if (input.length() < 3){
            return false;
        }
        String[] s = fromCB.split(" ");
        fromCB = s[s.length-1];
        return fromCB.startsWith(input.substring(0,2));
    }

    private boolean checkSingle(String input, String fromCb) {
        if (input.equals(fromCb)){
            return true;
        }
        if (input.length() > fromCb.length()){
            return false;
        }
        if (input.length() < 3){
            return false;
        }
        for (int i = 0; i <= fromCb.length() - input.length(); i++){
            if (input.equalsIgnoreCase(fromCb.substring(i,i+input.length()))){
                return true;
            }
        }
        return false;
    }
}
