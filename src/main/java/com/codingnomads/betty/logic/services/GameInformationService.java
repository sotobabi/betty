package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betAPImodels.MarketJSON;
import com.codingnomads.betty.logic.models.betAPImodels.RunnerJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameInformationService {

    private GameInformationRepository gameInformationRepository;
    private MatchOddsJpaRepository matchOddsJpaRepository;
    private GameInformationJSON gameInformationJSON;
    private EventJSON eventJSON;
    private MatchOdds matchOdds;

    @Autowired
    public GameInformationService(GameInformationRepository gameInformationRepository, MatchOddsJpaRepository matchOddsJpaRepository) {
        this.gameInformationRepository = gameInformationRepository;
        this.matchOddsJpaRepository = matchOddsJpaRepository;
    }

    public Map<String, Double> getOddsByMatch(String team1, String team2) {

        gameInformationJSON = getGameInformation();

        String match = team1 + " vs " + team2;

        eventJSON = getEventJSONforMatch(match, gameInformationJSON);

        MarketJSON marketJSON = getMarketJSONforMatch(eventJSON);

        Double oddForTeam1 = getRunnerJSONforMarket(marketJSON, team1).getPriceJSONS().get(0).getOdds();
        Double oddForTeam2 = getRunnerJSONforMarket(marketJSON, team2).getPriceJSONS().get(0).getOdds();

        Map<String, Double> odds = new HashMap();

        odds.put(team1, oddForTeam1);
        odds.put(team2, oddForTeam2);

        return odds;
    }

    public MatchOdds saveMatchOdds(String homeTeamName, String awayTeamName){

        Map<String, Double> odds = getOddsByMatch(homeTeamName, awayTeamName);

        matchOdds = new MatchOdds();

        matchOdds.setHomeTeam(homeTeamName);
        matchOdds.setHomeTeamOdd(odds.get(homeTeamName));

        matchOdds.setAwayTeam(awayTeamName);
        matchOdds.setAwayTeamOdd(odds.get(awayTeamName));

        matchOdds.setMatchDateTime(getFormattedDate());

        return matchOddsJpaRepository.save(matchOdds);
    }

    private GameInformationJSON getGameInformation() {

        return gameInformationRepository.getGameInformation();
    }

    private EventJSON getEventJSONforMatch(String match, GameInformationJSON gameInformationJSON) {

        for (EventJSON event : gameInformationJSON.getEventJSONS()) {

            if (event.getName().equalsIgnoreCase(match)) {

                return event;
            }
        }

        throw new JSONNotFoundException("Game Not Found!");
    }

    private MarketJSON getMarketJSONforMatch(EventJSON eventJSON){

        for (MarketJSON marketJSON: eventJSON.getMarketJSONS()) {

            if(marketJSON.getName().equalsIgnoreCase("Match Odds")){

                return marketJSON;
            }
        }

        throw new JSONNotFoundException("There are No Match Odds For This Game!");
    }

    private RunnerJSON getRunnerJSONforMarket(MarketJSON marketJSON, String teamName){

        for (RunnerJSON runnerJSON : marketJSON.getRunnerJSONS()){

            if(runnerJSON.getName().equalsIgnoreCase(teamName)){

                return runnerJSON;
            }
        }

        throw new JSONNotFoundException("Team Not Found in RunnerJSON!");
    }

    private Date getFormattedDate(){

        String dateFromAPI = eventJSON.getStart().substring(0,11);

        dateFromAPI = dateFromAPI.replace('-', '/');
        dateFromAPI = dateFromAPI.replace('T', ' ');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date date = new Date();

        try {
            date = sdf.parse(dateFromAPI);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long mills = date.getTime();

        return new Date(mills);
    }
}
