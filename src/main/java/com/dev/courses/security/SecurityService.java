package com.dev.courses.security;

import com.dev.courses.api.AuthenticationData;

public interface SecurityService {

    AuthenticationData authenticate(String username, String password);
    AuthenticationData getAuthenticationSession(String token);


}
