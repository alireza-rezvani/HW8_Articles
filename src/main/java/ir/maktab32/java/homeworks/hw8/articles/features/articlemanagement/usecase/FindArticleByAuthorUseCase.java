package ir.maktab32.java.homeworks.hw8.articles.features.articlemanagement.usecase;

import ir.maktab32.java.homeworks.hw8.articles.entities.Article;

import java.util.List;

public interface FindArticleByAuthorUseCase {
    List<Article> list(String author);
}
