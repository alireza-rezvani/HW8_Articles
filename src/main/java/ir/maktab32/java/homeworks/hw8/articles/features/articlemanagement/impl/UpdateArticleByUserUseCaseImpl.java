package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase.UpdateArticleByUserUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class UpdateArticleByUserUseCaseImpl implements UpdateArticleByUserUseCase {
    @Override
    public Article update(Integer id, Article article) {
        Article result = article;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Article oldArticle = session.load(Article.class, id);
        oldArticle.setTitle(article.getTitle());
        oldArticle.setBrief(article.getBrief());
        oldArticle.setContent(article.getContent());
        oldArticle.setIsPublished(article.getIsPublished());
        oldArticle.setLastUpdateDate(article.getLastUpdateDate());
        session.update(oldArticle);
        session.getTransaction().commit();
        return result;
    }
}
