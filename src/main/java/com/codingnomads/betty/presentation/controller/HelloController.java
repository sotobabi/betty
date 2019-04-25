package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.data.api.TwitterSourceRepository;
import com.codingnomads.betty.logic.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private static final String keyword = "cat";
    private static final int numberOfTweets = 15;

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;
    @Autowired
    private TwitterService twitterService;

    @GetMapping("/")
    public String sayHello() {
        twitterService.searchTweets(keyword, numberOfTweets);
        System.out.println("<---------------STUFF GOT DONE----------------->");
        return "hello";
    }
}
