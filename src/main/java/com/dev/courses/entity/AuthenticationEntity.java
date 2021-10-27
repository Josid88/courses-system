package com.dev.courses.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "auth_entity")
public class AuthenticationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "u_id")
    private String uId;

    private String username;
    private String password;

}
