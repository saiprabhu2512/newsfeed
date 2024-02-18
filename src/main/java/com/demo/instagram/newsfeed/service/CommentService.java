package com.demo.instagram.newsfeed.service;

import com.demo.instagram.newsfeed.dto.CommentDto;
import com.demo.instagram.newsfeed.exception.PostException;
import com.demo.instagram.newsfeed.model.Comment;
import com.demo.instagram.newsfeed.model.Post;
import com.demo.instagram.newsfeed.repo.CommentRepository;
import com.demo.instagram.newsfeed.repo.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    private ModelMapper mapper;

    List<CommentDto> newCommentList = new ArrayList<>();

    public CommentDto createComment(CommentDto dto, Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException("you are entered id is not existed"));

        Comment comment = mapper.map(dto, Comment.class);
        Comment newComment = null;
        comment.setPost(post);
        try {
            newComment = commentRepository.save(comment);
        } catch (Exception e) {
            throw new PostException("your entered email is already existed");
        }

        CommentDto newCommentDto = mapper.map(newComment, CommentDto.class);

        return newCommentDto;

    }

    public List<CommentDto> getAllComments(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        commentRepository.findAll(pageable).forEach(comment -> {

            CommentDto newCommentDto = mapper.map(comment, CommentDto.class);
            newCommentList.add(newCommentDto);
        });

        return newCommentList;

    }
    public List<CommentDto> getAllCommentsForPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        commentRepository.findAll(pageable).forEach(comment -> {

            CommentDto newCommentDto = mapper.map(comment, CommentDto.class);
            newCommentList.add(newCommentDto);
        });

        return newCommentList;

    }

    public CommentDto getCommentByID(Long id) {

        Comment newComment = commentRepository.findById(id)
                .orElseThrow(() -> new PostException("Comments are not available for this Id"));
        CommentDto newCommentDto = mapper.map(newComment, CommentDto.class);

        return newCommentDto;
    }

    public void deleteCommentById(Long id) {

        // commentRepository.deleteById(id);
        /*
         * newCommentList =newCommentList.stream().filter(comment->{
         * if(comment.getId()!=id) { return true; }else { return false; }
         * }).collect(Collectors.toList());
         */
        commentRepository.deleteById(id);
    }

    public CommentDto updateComment(CommentDto dto, Long id) {

        Comment newComment = commentRepository.findById(id).orElseThrow(()->new PostException("you are entered id is not available"));

        if (newComment.getId().equals(id)) {
            newComment.setEmail(dto.getEmail());
        }
        Comment newCommentUpdate = commentRepository.save(newComment);
        CommentDto newDto = mapper.map(newCommentUpdate, CommentDto.class);
        return newDto;

    }


}
