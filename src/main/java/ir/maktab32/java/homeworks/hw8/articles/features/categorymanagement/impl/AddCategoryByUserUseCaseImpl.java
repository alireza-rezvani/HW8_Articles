package ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Category;
import ir.maktab32.java.homeworks.hw8.articles.features.categorymanagement.usecase.AddCategoryByUserUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddCategoryByUserUseCaseImpl implements AddCategoryByUserUseCase {
    @Override
    public Category add(Category category) {
        Category result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(category);
        System.out.println("\t\t\t\u2705 Catogory Created. Id: " + id);
        result = session.load(Category.class, id);
        session.getTransaction().commit();
        return result;
    }
}
