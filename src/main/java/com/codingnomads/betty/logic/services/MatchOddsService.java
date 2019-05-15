package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betAPImodels.MarketJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class MatchOddsService {

    private GameInformationRepository gameInformationRepository;
    private MatchOddsJpaRepository matchOddsJpaRepository;

    @Autowired
    public MatchOddsService(GameInformationRepository gameInformationRepository, MatchOddsJpaRepository matchOddsJpaRepository) {
        this.gameInformationRepository = gameInformationRepository;
        this.matchOddsJpaRepository = matchOddsJpaRepository;
    }

    public MatchOdds createMatchOdds(String match) {

        String homeTeamName = match.split(" vs ")[0];
        String awayTeamName = match.split(" vs ")[1];

        GameInformationJSON gameInformationJSON = gameInformationRepository.getGameInformation();

        EventJSON eventJSON = gameInformationRepository.getEventJSONforMatch(match, gameInformationJSON);

        MarketJSON marketJSON = gameInformationRepository.getMarketJSONforMatch(eventJSON);

        Double homeTeamOdd = gameInformationRepository.getRunnerJSONforMarket(marketJSON, homeTeamName).getPriceJSONS().get(0).getOdds();
        Double awayTeamOdd = gameInformationRepository.getRunnerJSONforMarket(marketJSON, awayTeamName).getPriceJSONS().get(0).getOdds();

        Instant instant = Instant.parse(eventJSON.getStart());

        MatchOdds matchOdds = new MatchOdds();

        matchOdds.setHomeTeam(homeTeamName);
        matchOdds.setAwayTeam(awayTeamName);
        matchOdds.setHomeTeamOdd(homeTeamOdd);
        matchOdds.setAwayTeamOdd(awayTeamOdd);
        matchOdds.setMatchDate(Date.from(instant));

        return matchOdds;
    }

    public List<MatchOdds> saveMatchOddsList(List<MatchOdds> list){
        return matchOddsJpaRepository.saveAll(list);
    }

    public MatchOdds findLatestInstanceInMatchOddsTable(){

        return matchOddsJpaRepository.findLatestInstanceInMatchOddsTable();
    }

    public List<MatchOdds> findMostRecentMatchesAndOdds() {
        List<MatchOdds> mostCurrentOddsAndMatches = matchOddsJpaRepository.findAll();
        return mostCurrentOddsAndMatches;
    }
}
