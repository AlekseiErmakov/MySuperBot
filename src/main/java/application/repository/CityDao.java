package application.repository;



import application.factory.HibernateSessionFactoryUtil;
import application.model.cities.City;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CityDao {

    public List<City> findAll(){

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM City a",City.class).getResultList();
    }

}
