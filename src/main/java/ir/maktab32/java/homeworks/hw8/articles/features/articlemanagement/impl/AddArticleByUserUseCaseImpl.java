package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.AddArticleByUserUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddArticleByUserUseCaseImpl implements AddArticleByUserUseCase {
    @Override
    public Article add(Article article) {
        Article result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(article);
        result = session.load(Article.class, id);
        session.getTransaction().commit();
        return result;
    }
}
