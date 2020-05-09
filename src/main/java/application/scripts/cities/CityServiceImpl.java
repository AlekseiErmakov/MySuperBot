package application.scripts.cities;

import application.scripts.CityNotFoundException;
import application.scripts.cities.dao.CityDao;
import application.scripts.cities.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private String EMPTY = "";
    private CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }


    @Override
    public boolean isPresent(String userCity) {
        return findAll().stream().anyMatch(city -> userCity.toLowerCase().equals(city.getName().toLowerCase()));
    }

    @Override
    public String findNextCity(String userCity, Set<String> cityNames) {
        List<String> validCityNames;
        try {
            validCityNames = findValidCityNames(userCity);
        } catch (CityNotFoundException ex) {
            return EMPTY;
        }

        if (cityNames.containsAll(validCityNames)) {
            return EMPTY;
        }
        validCityNames.removeAll(cityNames);
        return getRandomCity(validCityNames);
    }

    @Override
    public List<String> findValidCityNames(String userCity) {
        System.out.println(userCity + "Пустое 0_о");
        for (int i = userCity.length() - 1; i >= 0; i--) {
            System.out.println(userCity.substring(i, i + 1));
            List<String> validCities = findCitiesStartsWith(userCity.substring(i, i + 1).toLowerCase());
            if (validCities.size() > 0) {
                return validCities;
            }
        }
        throw new CityNotFoundException();
    }



    private List<String> findCitiesStartsWith(String start) {
        return findAll().stream()
                .filter(city -> city.getName().toLowerCase().startsWith(start))
                .map(City::getName)
                .collect(Collectors.toList());
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }
    public String getRandomCity(List<String> cities) {
        Random random = new Random();
        int index = random.nextInt(cities.size());
        return cities.get(index);
    }
}
