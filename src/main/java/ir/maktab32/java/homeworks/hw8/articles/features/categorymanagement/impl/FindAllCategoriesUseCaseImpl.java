package ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Category;
import ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.usecase.FindAllCategoriesUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class FindAllCategoriesUseCaseImpl implements FindAllCategoriesUseCase {
    @Override
    public List<Category> list() {
        List<Category> result = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        result = session.createQuery("from Category").list();
        session.getTransaction().commit();
        return result;
    }
}
