package com.dev.courses.webapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateInitializer {

    public SessionFactory init() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/courses");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "root");
        properties.put("hibernate.archive.autodetection", "class, hbm");

        properties.put("show_sql", "true");
        properties.put("hbm2ddl.auto", "update");

        Configuration configuration = new Configuration().setProperties(properties);
        return configuration.buildSessionFactory();
    }
}
