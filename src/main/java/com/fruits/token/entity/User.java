package com.fruits.token.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer userId;
    private String username;
    private String password;

}
