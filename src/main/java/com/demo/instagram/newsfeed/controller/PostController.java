package com.demo.instagram.newsfeed.controller;

import com.demo.instagram.newsfeed.dto.PostDto;
import com.demo.instagram.newsfeed.model.Post;
import com.demo.instagram.newsfeed.service.PostService;
import com.demo.instagram.newsfeed.service.ResponseValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ResponseValidateService service;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/posts")
    public ResponseEntity<?> uploadPost(@Validated @RequestBody PostDto dto, BindingResult bindingResult) {


        if (service.validateData(bindingResult) != null) {
            return service.validateData(bindingResult);

        } else {
            return new ResponseEntity<Post>(postService.uploPost(dto), HttpStatus.CREATED);
        }

    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir) {

        return new ResponseEntity<List<PostDto>>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {

        return new ResponseEntity<PostDto>(postService.getPostByID(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping("posts/{id}")
    public ResponseEntity<?> updatepost(@PathVariable Long id, @Validated @RequestBody PostDto dto,
                                        BindingResult bindingResult) {

        if (service.validateData(bindingResult) != null) {
            return service.validateData(bindingResult);

        } else {

            return new ResponseEntity<PostDto>(postService.updatePost(dto, id), HttpStatus.OK);
        }
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
