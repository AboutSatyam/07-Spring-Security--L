
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
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        Users users = repo.findByName(userName);

        if (users == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(users);
    }
}
