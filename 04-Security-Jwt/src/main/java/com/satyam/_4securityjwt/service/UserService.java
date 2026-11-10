package com.satyam._4securityjwt.service;

import com.satyam._4securityjwt.entity.Users;
import com.satyam._4securityjwt.rest.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepo repo;

    public Users register(Users user) {
        return repo.save(user);

    }

}
