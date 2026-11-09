
package com.satyam._2securityproject2.rest;

import com.satyam._2securityproject2.model.Alien;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlienController {

    List<Alien> list = new ArrayList<>(List.of(
            new Alien(1, "Rohan", "Bengularu"),
            new Alien(2, "Rohit", "Bengal")
    ));

    @GetMapping("/get-aliens")
    public List<Alien> getAliens() {
        return list;
    }

    @PostMapping("/add-aliens")
    public void addAlien(@RequestBody Alien alien) {
        list.add(alien);
        System.out.println(list);
    }

    @GetMapping("/get-info")
    public String getCourseInfo(HttpServletRequest request) {
        return "Hello World " + request.getSession().getId();
    }

    @GetMapping("/more-info")
    public String getCourseMoreInfo(HttpServletRequest request) {
        return "Hello World " + request.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken getCrsfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
