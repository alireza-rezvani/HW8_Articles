package ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.usecase.FindAllUsersUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FindAllUsersUseCaseImpl implements FindAllUsersUseCase {
    @Override
    public List<User> list() {
        List<User> result;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        result = session.createQuery("from User ").list();
        session.getTransaction().commit();
        return result;
    }
}
