package com.demo.instagram.newsfeed.service;

import com.demo.instagram.newsfeed.dto.PostDto;
import com.demo.instagram.newsfeed.exception.PostException;
import com.demo.instagram.newsfeed.model.Post;
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
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Post uploPost(PostDto dto) {

        Post post = modelMapper.map(dto, Post.class);
        try {
            return postRepository.save(post);


        } catch (Exception e) {
            throw new PostException("Given input is already existed");
        }
        /*
         * Post post= converter.dtoToModel(dto); return postRepository.save(post);
         */
    }





    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize,sort);

        List<PostDto> postDtoList = new ArrayList<>();

        postRepository.findAll(pageable).getContent().forEach(post -> {
            PostDto newDto = modelMapper.map(post, PostDto.class);
            postDtoList.add(newDto);
        });

        return postDtoList;

    }

    public PostDto getPostByID(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException("Posts are not available for this Id"));
        PostDto newPostDto = modelMapper.map(post, PostDto.class);

        return newPostDto;
    }

    public PostDto updatePost(PostDto dto, Long id) {

        Post newPost = postRepository.findById(id).orElseThrow(()->new PostException("you are entered id is not available"));

        if (newPost.getId().equals(id)) {
            newPost.setTitle(dto.getTitle());
            newPost.setDescription(dto.getDescription());
        }
        Post newPostUpdate = postRepository.save(newPost);
        PostDto newDto = modelMapper.map(newPostUpdate, PostDto.class);
        return newDto;

    }
    public void deletePostById(Long id) {

        postRepository.deleteById(id);
    }

}
