package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.data.api.TwitterSourceRepository;
import com.codingnomads.betty.logic.models.TeamProbabilityToWin;
import com.codingnomads.betty.logic.services.TwitterService;
import com.codingnomads.betty.presentation.webmodel.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private TwitterSourceRepository twitterSourceRepository;

    @GetMapping("/search")
    public String search(@RequestParam(name="teamName", required = false) String teamName, Model model){
        model.addAttribute("searchRequest", new SearchRequest());
        //todo
        model.addAttribute("teamProbabilityToWin", new TeamProbabilityToWin());
        return "/search";
    }
}
