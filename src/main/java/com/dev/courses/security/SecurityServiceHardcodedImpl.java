package com.dev.courses.security;

import com.dev.courses.api.AuthenticationData;

import java.util.HashMap;
import java.util.Map;

public class SecurityServiceHardcodedImpl implements SecurityService {

    Map<String, String> authMap = new HashMap<>();

    {
        authMap.put("admin:admin", "c520035b-5aee-4529-ba11-a0519fd74f9e");
    }


    @Override
    public AuthenticationData authenticate(String username, String password) {
        String uuid = authMap.get(username + ":" + password);
        AuthenticationData result = null;

        if (uuid == null) {
            result = new AuthenticationData(AuthenticationStatus.REJECT, null, null, null);
        } else {
            result = new AuthenticationData(AuthenticationStatus.AUTHENTICATED, uuid, "GEN TOKEN", username);
        }

        return  result;
    }

}
