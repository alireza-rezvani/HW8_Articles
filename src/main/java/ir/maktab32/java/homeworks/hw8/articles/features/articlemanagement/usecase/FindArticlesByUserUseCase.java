package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase;

import ir.maktab32.java.homeworks.hw8.articles.entities.Article;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;

import java.util.List;

public interface FindArticlesByUserUseCase {
    List<Article> articlesList(User user);
}
