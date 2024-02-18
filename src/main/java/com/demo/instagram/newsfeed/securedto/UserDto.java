package com.demo.instagram.newsfeed.securedto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString
public class UserDto {

    private Long uid;

    private String name;
    @NotBlank(message = "Enter your email required")
    @Size(min = 5,message = "your email should be min 5 char")
    private String email;
    @NotBlank(message = "Enter your username required")
    private String username;
    @NotBlank(message = "Enter Password reqired")
    @Size(min = 5,message = "password must be min 5 chars")
    private String password;
}
