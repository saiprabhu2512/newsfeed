package com.demo.instagram.newsfeed.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotBlank(message ="valid title is required")
    @Size(min =3,message = "your title should be minimum 3 characters")
    @Size(max = 15,message = "your title shold be maximum 15 characters")
    private String title;
    @NotBlank(message ="post description is required")
    private String description;
    @NotBlank(message ="post content is required")
    private String content;

}
