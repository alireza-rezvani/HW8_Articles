package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.FindArticleByTitleUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FindArticleByTitleUseCaseImpl implements FindArticleByTitleUseCase {
    @Override
    public List<Article> list(String title) {
        List<Article> result;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where title = :title");
        query.setString("title", title);
        result = query.list();
        session.getTransaction().commit();
        return result;
    }
}
