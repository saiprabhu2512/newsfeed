package com.demo.instagram.newsfeed.secureservice;

import com.demo.instagram.newsfeed.model.User;
import com.demo.instagram.newsfeed.securerepo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder encoader;
    public User saveUser(User user) {
        user.setPassword(encoader.encode(user.getPassword()));
        User user2=	repository.save(user);

        return user2;

    }
}
