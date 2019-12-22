package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.FindArticlesByUserUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FindArticlesByUserUseCaseImpl implements FindArticlesByUserUseCase {
    @Override
    public List<Article> articlesList(User user) {
        List<Article> result = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where author = " + user.getUsername());
        result = query.list();
        session.getTransaction().commit();
        return result;
    }
}
