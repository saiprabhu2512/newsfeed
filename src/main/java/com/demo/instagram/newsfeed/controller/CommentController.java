package com.demo.instagram.newsfeed.controller;

import com.demo.instagram.newsfeed.dto.CommentDto;
import com.demo.instagram.newsfeed.service.CommentService;
import com.demo.instagram.newsfeed.service.ResponseValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ResponseValidateService service;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> createComment(@PathVariable("id") Long id, @Validated @RequestBody CommentDto dto,
                                           BindingResult bindingResult) {

        if (service.validateCommentData(bindingResult) != null) {
            return service.validateCommentData(bindingResult);
        } else {

            return new ResponseEntity<CommentDto>(commentService.createComment(dto, id), HttpStatus.CREATED);

        }

    }
    @GetMapping("posts/{id}/comments")
    public ResponseEntity<?> getAllCommentsForPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return new ResponseEntity<List<CommentDto>>(commentService.getAllComments(pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK);
    }

    @GetMapping("posts/comments")
    public ResponseEntity<?> getAllComments(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return new ResponseEntity<List<CommentDto>>(commentService.getAllComments(pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id) {

        return new ResponseEntity<CommentDto>(commentService.getCommentByID(id), HttpStatus.OK);

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {

        commentService.deleteCommentById(id);
        return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<?>updateComment(@PathVariable("id") Long id,@Validated @RequestBody CommentDto dto,BindingResult bindingResult) {

        if (service.validateCommentData(bindingResult) != null) {
            return service.validateCommentData(bindingResult);
        } else {
            return new ResponseEntity<CommentDto>(commentService.updateComment(dto, id), HttpStatus.OK);

        }


    }
}
