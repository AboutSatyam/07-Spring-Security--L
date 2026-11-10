
package com.satyam._4securityjwt.rest;

import com.satyam._2securityproject2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<Users, Integer> {

    Users findByName(String userName);
}
