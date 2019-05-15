package com.codingnomads.betty.data.batch.matchoddsjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.MatchOddsService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchToOddsProcessor implements ItemProcessor<List<FootballMatchInfo>, List<MatchOdds>> {

    private MatchOddsService matchOddsService;

    @Autowired
    public MatchToOddsProcessor(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @Override
    public List<MatchOdds> process(List<FootballMatchInfo> item) throws Exception {

        List<MatchOdds> oddsList = new ArrayList<>();

        for (FootballMatchInfo matchInfo : item) {

            MatchOdds odds = matchOddsService.createMatchOdds(matchInfo.getName());

            oddsList.add(odds);
        }

        return oddsList;
    }
}

