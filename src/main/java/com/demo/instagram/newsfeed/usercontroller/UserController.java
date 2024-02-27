package com.demo.instagram.newsfeed.usercontroller;

import com.demo.instagram.newsfeed.securedto.LoginDto;
import com.demo.instagram.newsfeed.securedto.RegisterDto;
import com.demo.instagram.newsfeed.secureservice.UserSecureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class UserController {

    @Autowired
    private UserSecureService service;

    @PostMapping(value = {"/register/signup"})
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto dto){

        String userdetails=	service.registerUser(dto);

        return new ResponseEntity<String>(userdetails, HttpStatus.CREATED);

    }

    @PostMapping(value = {"/login/signin"})
    public ResponseEntity<?>logUser(@RequestBody LoginDto dto){

        String logUser=	service.userLogin(dto);

        return new ResponseEntity<String>(logUser, HttpStatus.OK);

    }
}


