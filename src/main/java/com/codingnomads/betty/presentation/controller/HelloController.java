package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.data.api.TwitterSourceMinerRepository;
import com.codingnomads.betty.logic.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private TwitterSourceMinerRepository twitterSourceRepository;
    @Autowired
    private TwitterService twitterService;

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/api-to-database")
    public String makeApiCallAndSinkTweetsToRemoteDatabase() {
        twitterService.callApiAndSaveStatusesAsTweets("cat", 15);
        return "api-to-database";
    }


}
