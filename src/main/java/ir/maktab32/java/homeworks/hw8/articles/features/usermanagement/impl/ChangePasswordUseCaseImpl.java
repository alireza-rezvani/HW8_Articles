package ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw8.articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw8.articles.entities.User;
import ir.maktab32.java.homeworks.hw8.articles.features.usermanagement.usecase.ChangePasswordUseCase;
import ir.maktab32.java.homeworks.hw8.articles.share.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {
    @Override
    public void setNewPass(String newPass) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.load(User.class, AuthenticationService.getInstance().getSignedInUser().getId());
        user.setPassword(newPass);
        session.update(user);
        session.getTransaction().commit();
    }
}
