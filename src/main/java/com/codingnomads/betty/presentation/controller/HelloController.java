package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import twitter4j.TwitterException;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }
}
