package com.dev.courses.webapp;

import com.dev.courses.entity.UserEntity;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


@Setter
public class HibernateInitializer {

    private String username = "root";
    private String password = "root123";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/courses";


    public SessionFactory init() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        properties.put("hibernate.connection.url", jdbcUrl);
        properties.put("hibernate.connection.username", username);
        properties.put("hibernate.connection.password", password);

        Configuration configuration = new Configuration().setProperties(properties)
                .addAnnotatedClass(UserEntity.class);

        return configuration.buildSessionFactory();
    }

}
