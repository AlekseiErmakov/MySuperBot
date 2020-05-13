package application.repository;


import application.factory.HibernateSessionFactoryUtil;
import application.model.users.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class CustomerRepositoryImpl implements CustomerRepository{


    @Override
    public Customer findByTelegramId(Integer telegramId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE telegramId =:param", Customer.class);
        query.setParameter("param",telegramId);
        Customer singleResult = null;
        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException ex){

        }
        return singleResult;
    }

    @Override
    public Customer save(Customer customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        return findByTelegramId(customer.getTelegramId());
    }
}
