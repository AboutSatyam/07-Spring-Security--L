package com.satyam._2securityproject2.service;

import com.satyam._2securityproject2.entity.Users;
import com.satyam._2securityproject2.rest.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepo repo;

    public Users register(Users user) {
        return repo.save(user);

    }

}
