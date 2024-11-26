package lk.ijse.studentmanagementsystem.config;

import lk.ijse.studentmanagementsystem.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){
        sessionFactory = new Configuration()
                .configure().
                addAnnotatedClass(Student.class).
                addAnnotatedClass(User.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Student_Course.class).
                addAnnotatedClass(Payment.class).
                buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (factoryConfig == null) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
