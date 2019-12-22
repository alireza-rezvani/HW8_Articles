package ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.usecase;

import ir.maktab32.java.homeworks.hw8.articles.entities.User;

public interface SignInUseCase {
    User signIn(String username, String password);
}
