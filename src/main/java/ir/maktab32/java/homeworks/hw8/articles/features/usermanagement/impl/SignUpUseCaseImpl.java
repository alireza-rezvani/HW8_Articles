package ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.usecase.SignUpUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SignUpUseCaseImpl implements SignUpUseCase {
    @Override
    public User signUp(User user) {
        User result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(user);
        System.out.println("Your User Id: " + id);
        result = session.load(User.class, id);
        session.getTransaction().commit();
        return null;
    }
}
