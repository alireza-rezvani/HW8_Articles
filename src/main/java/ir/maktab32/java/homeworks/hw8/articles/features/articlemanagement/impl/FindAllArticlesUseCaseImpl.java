package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.FindAllArticlesUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FindAllArticlesUseCaseImpl implements FindAllArticlesUseCase {
    @Override
    public List<Article> articleList() {
        List<Article> result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article ");
        result = query.list();
        session.getTransaction().commit();
        return result;
    }
}
