package com.satyam._4securityjwt.rest;

import com.satyam._4securityjwt.entity.Users;
import com.satyam._4securityjwt.service.JwtTokenService;
import com.satyam._4securityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeluskoController {


    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserService service;
    @Autowired
    private JwtTokenService jwtService;

    @PostMapping("/add-user")
    public Users registerNewUser(@RequestBody Users user) {

        String encoded = bcrypt.encode(user.getPassword());
        user.setPassword(encoded);
        return service.register(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (authentication.isAuthenticated()) {

            String jwt = jwtService.generateToken(user.getName());
            System.out.println(jwt);
            return jwt;

        } else
            return "InValid Credentials ";
    }

    @GetMapping("/get-courseinfo")
    public String getCourseInfo() {

        return "Telusko Devops Course I Already Have In My TODO List";
    }

}
