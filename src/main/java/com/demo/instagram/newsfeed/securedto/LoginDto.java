package com.demo.instagram.newsfeed.securedto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginDto {

    private String username;
    private String pwd;
}
