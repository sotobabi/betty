package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.data.api.TwitterSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }
}
