package com.dev.courses.api;

import com.dev.courses.security.AuthenticationStatus;
import lombok.*;


@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class AuthenticationData {

    private AuthenticationStatus status;
    private String id;
    private String token;
    private String username;

}
