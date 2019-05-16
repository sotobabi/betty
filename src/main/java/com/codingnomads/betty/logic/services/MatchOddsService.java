package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.models.betapimodels.EventJSON;
import com.codingnomads.betty.logic.models.betapimodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betapimodels.MarketJSON;
import com.codingnomads.betty.logic.models.betapimodels.RunnerJSON;
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

        GameInformationJSON gameInformationJSON = gameInformationRepository.getGameInformation();

        EventJSON eventJSON = gameInformationRepository.getEventJSONforMatch(match, gameInformationJSON);

        MarketJSON marketJSON = gameInformationRepository.getMarketJSONforMatch(eventJSON);

        String homeTeamName = eventJSON.getName().split(" vs ")[0];
        String awayTeamName = eventJSON.getName().split(" vs ")[1];

        RunnerJSON homeRunner = gameInformationRepository.getRunnerJSONforMarket(marketJSON, homeTeamName);
        RunnerJSON awayRunner = gameInformationRepository.getRunnerJSONforMarket(marketJSON, awayTeamName);

        Double homeTeamOdd = homeRunner.getPriceJSONS().get(0).getOdds();
        Double awayTeamOdd = awayRunner.getPriceJSONS().get(0).getOdds();

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

    public List<MatchOdds> findMostRecentMatchesAndOdds() {
        return matchOddsJpaRepository.findAll();
    }
}
