package com.satyam._2securityproject2.rest;

import com.satyam._2securityproject2.entity.Users;
import com.satyam._2securityproject2.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<Alien, String> {
    Users findByName(String userName);

}
