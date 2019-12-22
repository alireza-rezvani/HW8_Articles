package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase;

import ir.maktab32.java.homeworks.hw8.articles.entities.Article;

public interface UpdateArticleByUserUseCase {
    Article update(Integer id, Article article);
}
