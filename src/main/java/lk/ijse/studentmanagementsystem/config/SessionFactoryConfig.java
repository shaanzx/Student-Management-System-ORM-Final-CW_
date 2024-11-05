package lk.ijse.studentmanagementsystem.config;

import lk.ijse.studentmanagementsystem.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private SessionFactoryConfig() {}

    public static SessionFactoryConfig getInstance() {
        return (factoryConfig == null) ?
                factoryConfig =  new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        Metadata metaData =
                new MetadataSources(serviceRegistry).
                        addAnnotatedClass(Student.class).getMetadataBuilder().build();

        SessionFactory sessionFactory = metaData.buildSessionFactory();

        return sessionFactory.openSession();

    }
}
