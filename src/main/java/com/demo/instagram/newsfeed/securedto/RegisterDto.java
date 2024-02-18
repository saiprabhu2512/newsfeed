package com.demo.instagram.newsfeed.securedto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterDto {

    private String username;
    private String email;
    private String password;
    private String name;
}
