package ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Category;
import ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.usecase.AddCategoryByUserUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddCategoryByUserUseCaseImpl implements AddCategoryByUserUseCase {
    @Override
    public void add(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(category);
        System.out.println("Catogory Created. Id: " + id);
        session.getTransaction().commit();
    }
}
