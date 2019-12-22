package ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.usecase.SignInUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class SignInUseCaseImpl implements SignInUseCase {
    @Override
    public User signIn(String username, String password) {
        User result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where username = :username and password = :password");
        query.setString("username", username);
        query.setString("password", password);
        result = (User) query.list().get(0);
        session.getTransaction().commit();
        return result;
    }
}
