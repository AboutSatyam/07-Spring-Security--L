package com.satyam._3security_oauth.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeluskoController {

    @GetMapping("/info")
    public String getCourseInfo() {
        return "Telusko Has Introduced Devops Course ";
    }
}