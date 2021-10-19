package com.dev.courses.security;

import com.dev.courses.api.AuthenticationData;

public interface SecurityService {

    public AuthenticationData authenticate(String username, String password);

}
