package com.demo.instagram.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommentDto {
    private Long id;
    @NotBlank(message = "name of the comment should required")
    private String name;
    @NotBlank(message = "email must required")
    @Size(min = 8,message = "your email should be min 8 characters")
    @Size(max = 25,message = "your email should not exceed 25 characters")
    private String email;
}
