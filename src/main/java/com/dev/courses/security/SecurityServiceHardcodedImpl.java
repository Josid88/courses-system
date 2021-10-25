package com.dev.courses.security;

import com.dev.courses.api.AuthenticationData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class SecurityServiceHardcodedImpl implements SecurityService {

    Map<String, String> authMap = new HashMap<>();
    Map<String, AuthenticationData> sessionMap = new HashMap<>();

    {
        authMap.put("admin:admin", "c520035b-5aee-4529-ba11-a0519fd74f9e");
    }

    @Override
    public AuthenticationData authenticate(String username, String password) {
        String userId = authMap.get(username + ":" + password);
        AuthenticationData authenticationResult;

        if (userId == null) {
            authenticationResult = new AuthenticationData(AuthenticationStatus.REJECT, null, null, null);

        } else {
            UUID uuid = UUID.randomUUID();
            authenticationResult = new AuthenticationData(AuthenticationStatus.AUTHENTICATED, userId, uuid.toString(), username);
            sessionMap.put(authenticationResult.getToken(), authenticationResult);
        }

        return  authenticationResult;
    }

    @Override
    public AuthenticationData getAuthenticationSession(String token) {
        return sessionMap.get(token);
    }

}
