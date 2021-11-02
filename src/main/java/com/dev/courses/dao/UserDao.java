package com.dev.courses.dao;

import com.dev.courses.entity.UserEntity;

public interface UserDao {

    UserEntity getUser(String username, String password);
    void save(UserEntity userEntity);

}
