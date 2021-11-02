package com.dev.courses.dao;

import com.dev.courses.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernate(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity getUser(String username, String password) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from UserEntity where username=:usr and password=:pwd");
        query.setParameter("usr", username);
        query.setParameter("pwd", password);
        List<UserEntity> resultList = query.getResultList();
        UserEntity userEntity = resultList.isEmpty() ? null : resultList.get(0);

        session.close();

        return userEntity;
    }

    @Override
    public void save(UserEntity userEntity) {
        Session session = sessionFactory.openSession();
        session.save(userEntity);
        session.close();
    }

}
