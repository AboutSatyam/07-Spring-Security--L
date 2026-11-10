package com.satyam._4securityjwt.rest;

import com.satyam._2securityproject2.entity.Users;
import com.satyam._2securityproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeluskoController {

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    @Autowired
    private UserService service;

    @PostMapping("/add-user")
    public Users registerNewUser(@RequestBody Users user) {

        String encoded = bcrypt.encode(user.getPassword());
        user.setPassword(encoded);
        return service.register(user);

    }

    @GetMapping("/get-courseinfo")
    public String getCourseInfo() {

        return "Telusko Devops Course I Already Have In My TODO List";
    }

}
