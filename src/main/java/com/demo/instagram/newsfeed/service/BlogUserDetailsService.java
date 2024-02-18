package com.demo.instagram.newsfeed.service;

import com.demo.instagram.newsfeed.model.Role;
import com.demo.instagram.newsfeed.model.User;
import com.demo.instagram.newsfeed.securerepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {

        User user=	repository.findByUsername(emailOrUserName).orElseThrow(()->new UsernameNotFoundException("username is invalid"));
        System.out.println(user);
//	SimpleGrantedAuthority se= user.getRoleslist().stream().map(role-> new SimpleGrantedAuthority(role))
//     .collect(Collectors.toList());
        List<GrantedAuthority> authorities=new ArrayList<>();



        for(Role roles:user.getRoleslist()) {
            authorities.add(new SimpleGrantedAuthority(roles.getRole()));

        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);

    }
}
