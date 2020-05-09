package application.scripts.cities;

import java.util.List;
import java.util.Set;


public interface CityService {
    boolean isPresent(String userCity);
    List<String> findValidCityNames(String curCity);
    String findNextCity(String userCity, Set<String> cityNames);
    String getRandomCity(List<String> cities);
}
