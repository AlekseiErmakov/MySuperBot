package application.scripts.cities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityContainer {
    @Getter
    private Set<String> cityNames;

    @Setter
    @Getter
    private String curCity = "";
    private CityService service;

    @Autowired
    public CityContainer(CityService service) {
        this.service = service;
        cityNames = new LinkedHashSet<>();
    }

    public void addCity(String cityName) {
        cityNames.add(cityName.toLowerCase());
    }

    public boolean containsCityName(String newCityName) {
        return cityNames.stream()
                .anyMatch(cityName -> cityName.equalsIgnoreCase(newCityName));
    }

    public boolean currentCityContainsLetter(String cityName) {
        if (!curCity.equals("")) {
            for (int i = 0; i < curCity.length(); i++) {
                if (curCity.substring(i, i + 1).equalsIgnoreCase(cityName.substring(0, 1))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    public boolean isValidCityName(String userCity) {
        if (curCity.isEmpty()){
            return true;
        }
        List<String> validCityNames = service.findValidCityNames(curCity);
        return validCityNames.stream()
                .anyMatch(cityName -> cityName.equalsIgnoreCase(userCity));
    }

    public boolean hasAnswer(String userCity) {
        List<String> validCities = service.findValidCityNames(userCity);
        return validCities.stream().anyMatch(cityName -> !cityNames.contains(cityName.toLowerCase()));
    }

    public String generateCityName(String userCity) {
        List<String> validCities = service.findValidCityNames(userCity);
        List<String> adorableVars = validCities.stream()
                .filter(cityName -> cityNames.contains(userCity.toLowerCase()))
                .collect(Collectors.toList());
        return service.getRandomCity(adorableVars);
    }
    public void clearAllData(){
        curCity="";
        cityNames.clear();
    }


}
