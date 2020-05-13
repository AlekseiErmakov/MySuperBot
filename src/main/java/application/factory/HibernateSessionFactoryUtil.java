package application.factory;

import application.model.users.Customer;
import application.model.users.UserRequest;
import application.model.cities.City;
import application.model.cities.Country;
import application.model.cities.Region;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(City.class);
            configuration.addAnnotatedClass(Region.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(UserRequest.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
