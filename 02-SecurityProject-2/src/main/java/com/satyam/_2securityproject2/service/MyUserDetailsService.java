package com.satyam._2securityproject2.service;

import com.satyam._2securityproject2.entity.Users;
import com.satyam._2securityproject2.rest.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = repo.findByName(username);
        if (users == null) {
            throw new UsernameNotFoundException("User Not Found 404")

        }
        return users;
    }
}
