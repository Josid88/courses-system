package com.dev.courses.webapp;

import com.dev.courses.dao.UserDao;
import com.dev.courses.dao.UserDaoHibernate;
import com.dev.courses.entity.UserEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.apache.log4j.BasicConfigurator;

import java.util.List;


public class HibernateInitializerTest {

    private HibernateInitializer testObject;
    private MySQLContainer<?> mySqlContainer;
    private SessionFactory sessionFactory;

    private UserDao userDao;

    @Before
    public void setUp() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel( Level.INFO );

        mySqlContainer = new MySQLContainer<>("mysql:8");
        mySqlContainer.withUsername("root");
        mySqlContainer.withPassword("root123");
        mySqlContainer.withDatabaseName("test");
        mySqlContainer.start();

        testObject = new HibernateInitializer();
        testObject.setJdbcUrl(mySqlContainer.getJdbcUrl());
        testObject.setUsername(mySqlContainer.getUsername());
        testObject.setPassword(mySqlContainer.getPassword());

        sessionFactory = testObject.init();
        userDao = new UserDaoHibernate(sessionFactory);
    }

    @Test
    public void insertWithDao() {
        UserEntity userEntity = createUserEntity("User1", "pass1", "12345");
        userDao.save(userEntity);
        userEntity = createUserEntity("User2", "pass2", "abcde");
        userDao.save(userEntity);
        userEntity = createUserEntity("User3", "pass3", "%$#@!");
        userDao.save(userEntity);

        UserEntity user = userDao.getUser("User1", "pass1");
        Assert.assertEquals("12345", user.getUId());

        user = userDao.getUser("User1", "pass123");
        Assert.assertNull(user);
    }

    @NotNull
    private UserEntity createUserEntity(String username, String password, String uuid) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setUId(uuid);
        return userEntity;
    }

}