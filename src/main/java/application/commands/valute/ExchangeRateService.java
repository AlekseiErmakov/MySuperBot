package application.commands.valute;

import org.springframework.stereotype.Service;


public interface ExchangeRateService {

    void updateCourses();

    boolean isPresent(String valute);

    String getExchangeRate(String valute);

}
