package application.repository;

import application.factory.HibernateSessionFactoryUtil;
import application.model.users.UserRequest;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class RequestRepositoryImpl implements RequestRepository {
    @Override
    public void save(UserRequest request) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(request);
        session.getTransaction().commit();
    }
}
