package application.scripts.cities;

import application.scripts.cities.dao.CityDao;
import org.junit.Before;
import org.junit.Test;

public class CitiesGameTest {
    private CityDao cityDao;
    private CityService cityService;
    private CitiesGame citiesGame;

    @Before
    public void setUp() throws Exception {
        cityDao = new CityDao();
        cityService = new CityServiceImpl(cityDao);

    }

    @Test
    public void processUserRequest() {

    }
}