package org.example.utils;

import lombok.Getter;
import org.example.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    @Getter
    private static final SessionFactory sesseionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
        } catch(Throwable ex) {
            System.out.println("Database connection error");
            throw new ExceptionInInitializerError(ex);
        }
    }

}