package ir.maktab32.java.homeworks.hw8.articles.share;

import ir.maktab32.java.homeworks.hw8.articles.entities.User;

public class AuthenticationService {
    private User SignedInUser;
    private static AuthenticationService authenticationService = null;
    public static AuthenticationService getInstance(){
        if (authenticationService == null)
            authenticationService = new AuthenticationService();
        return authenticationService;
    }

    public User getSignedInUser(){
        return SignedInUser;
    }

    public void setSignedInUser(User user){
        this.SignedInUser = user;
    }

}
