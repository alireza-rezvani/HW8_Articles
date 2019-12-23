package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.FindArticleByAuthorUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FindArticleByAuthorUseCaseImpl implements FindArticleByAuthorUseCase {
    @Override
    public List<Article> list(String author) {
        List<Article> result;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> matchedUserList = session.createQuery("from User where username = '" + author + "'").list();
        if (matchedUserList.size() != 0) {
            Query query = session.createQuery("from Article where author = " + matchedUserList.get(0).getId());
            result = query.list();
        }
        else
            result = new ArrayList<>();
        session.getTransaction().commit();
        return result;
    }
}
