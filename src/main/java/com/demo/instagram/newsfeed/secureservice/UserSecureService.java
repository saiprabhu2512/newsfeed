package com.demo.instagram.newsfeed.secureservice;

import com.demo.instagram.newsfeed.exception.MyBlogException;
import com.demo.instagram.newsfeed.jwtutil.JwtAuthProvider;
import com.demo.instagram.newsfeed.model.Role;
import com.demo.instagram.newsfeed.model.User;
import com.demo.instagram.newsfeed.securedto.LoginDto;
import com.demo.instagram.newsfeed.securedto.RegisterDto;
import com.demo.instagram.newsfeed.securerepo.RoleRepository;
import com.demo.instagram.newsfeed.securerepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserSecureService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository repository2;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAuthProvider authProvider;

    public UserSecureService(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }


    public String userLogin(LoginDto dto) {
        Authentication authentication= (Authentication) authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()) {
            return authProvider.generateToken(dto.getUsername());
        }else {
            throw new MyBlogException("invalid username or pwd", HttpStatus.BAD_REQUEST);
        }

    }


    public String registerUser(RegisterDto dto) {
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());


        Role rs=repository2.findByRole("ADMIN").get();

//		Role rs=new Role();
//		rs.setRid(1L);
//	Role rs1=new Role();
//		rs1.setRid(2L);
//
        List<Role> rolesList=new ArrayList<>();
        rolesList.add(rs);
//		rolesList.add(rs);
//	rolesList.add(rs1);
//
        user.setRoleslist(rolesList);
        repository.save(user);

        return "Registration successfully completed";

    }
}
