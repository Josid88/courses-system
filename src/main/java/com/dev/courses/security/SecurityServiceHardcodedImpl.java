package com.dev.courses.security;

import com.dev.courses.api.AuthenticationData;
import com.dev.courses.dao.UserDao;
import com.dev.courses.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class SecurityServiceHardcodedImpl implements SecurityService {

    private final UserDao userDao;
    private final Map<String, AuthenticationData> sessionMap = new HashMap<>();

    public SecurityServiceHardcodedImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public AuthenticationData authenticate(String username, String password) {
        UserEntity user = userDao.getUser(username, password);

        AuthenticationData authenticationResult;
        if (user == null) {
            authenticationResult = new AuthenticationData(AuthenticationStatus.REJECT, null, null, null);

        } else {
            UUID uuid = UUID.randomUUID();
            authenticationResult = new AuthenticationData(AuthenticationStatus.AUTHENTICATED, user.getUId(), uuid.toString(), username);
            sessionMap.put(authenticationResult.getToken(), authenticationResult);
        }

        return  authenticationResult;
    }

    @Override
    public AuthenticationData getAuthenticationSession(String token) {
        return sessionMap.get(token);
    }

}
