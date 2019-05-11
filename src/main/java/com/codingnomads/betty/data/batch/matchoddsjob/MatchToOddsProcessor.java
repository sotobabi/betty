package com.codingnomads.betty.data.batch.matchoddsjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchToOddsProcessor implements ItemProcessor<List<FootballMatchInfo>, List<MatchOdds>> {

    private GameInformationService gameInformationService;

    @Autowired
    public MatchToOddsProcessor(GameInformationService gameInformationService) {
        this.gameInformationService = gameInformationService;
    }

    @Override
    public List<MatchOdds> process(List<FootballMatchInfo> item) throws Exception {

        return gameInformationService.createOddsListFromFootballList(item);
    }
}

