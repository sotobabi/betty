package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
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
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameInformationService {

    private FootballMatchesInfoJpaRepository footballJpaRepository;
    private GameInformationRepository gameInformationRepository;
    private MatchOddsJpaRepository matchOddsJpaRepository;
    private GameInformationJSON gameInformationJSON;
    private EventJSON eventJSON;
    private MatchOdds matchOdds;

    @Autowired
    public GameInformationService(GameInformationRepository gameInformationRepository
            , MatchOddsJpaRepository matchOddsJpaRepository
            , FootballMatchesInfoJpaRepository footballJpaRepository) {

        this.gameInformationRepository = gameInformationRepository;
        this.matchOddsJpaRepository = matchOddsJpaRepository;
        this.footballJpaRepository = footballJpaRepository;
    }

    public Map<String, Double> getOddsByMatch(String homeTeamName, String awayTeamName) {

        gameInformationJSON = getGameInformation();

        String match = homeTeamName + " vs " + awayTeamName;

        eventJSON = getEventJSONforMatch(match, gameInformationJSON);

        MarketJSON marketJSON = getMarketJSONforMatch(eventJSON);

        Double oddForTeam1 = getRunnerJSONforMarket(marketJSON, homeTeamName).getPriceJSONS().get(0).getOdds();
        Double oddForTeam2 = getRunnerJSONforMarket(marketJSON, awayTeamName).getPriceJSONS().get(0).getOdds();

        Map<String, Double> odds = new HashMap();

        odds.put(homeTeamName, oddForTeam1);
        odds.put(awayTeamName, oddForTeam2);

        return odds;
    }

    public MatchOdds saveMatchOdds(String homeTeamName, String awayTeamName){

        matchOdds = getMatchOdds(homeTeamName, awayTeamName);

        return matchOddsJpaRepository.save(matchOdds);
    }

    public MatchOdds getMatchOdds(String homeTeamName, String awayTeamName){

        Map<String, Double> odds = getOddsByMatch(homeTeamName, awayTeamName);

        matchOdds = new MatchOdds();

        matchOdds.setHomeTeam(homeTeamName);
        matchOdds.setHomeTeamOdd(odds.get(homeTeamName));

        matchOdds.setAwayTeam(awayTeamName);
        matchOdds.setAwayTeamOdd(odds.get(awayTeamName));

        matchOdds.setMatchDate(getFormattedDate(eventJSON.getStart()));

        return matchOdds;
    }

    public List<MatchOdds> createOddsListFromFootballList(List<FootballMatchInfo> matchInfoList){

        List<MatchOdds> oddsList = new ArrayList<>();

        for (FootballMatchInfo matchInfo : matchInfoList) {

            String [] teams = matchInfo.getName().split(" vs ");

            MatchOdds odds = getMatchOdds(teams[0], teams[1]);

            oddsList.add(odds);
        }

        return oddsList;
    }

    public List<FootballMatchInfo> getFootballMatchListFromApi(){

        GameInformationJSON gameInformation = getGameInformation();

        List<FootballMatchInfo> footballMatchInfoList = new ArrayList<>();

        for (EventJSON event :gameInformation.getEventJSONS()) {

            FootballMatchInfo footballMatchInfo = new FootballMatchInfo();

            footballMatchInfo.setApi_id(event.getId());

            footballMatchInfo.setMatchDate(getFormattedDate(event.getStart()));

            footballMatchInfo.setName(event.getName());

            footballMatchInfoList.add(footballMatchInfo);
        }

        return footballMatchInfoList;
    }

    public List<FootballMatchInfo> saveFootballMatches(){

        List<FootballMatchInfo> footballMatchInfoList = getFootballMatchListFromApi();

         return footballJpaRepository.saveAll(footballMatchInfoList);
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

    private Date getFormattedDate(String dateFromAPI){

        dateFromAPI = dateFromAPI.substring(0,11);

        dateFromAPI = dateFromAPI.replace('-', '/');
        dateFromAPI = dateFromAPI.replace('T', ' ');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date date = new Date();

        try {
            date = sdf.parse(dateFromAPI);
        } catch (ParseException e) {
            throw new RuntimeException("Match Date Time Parsing Error!", e);
        }

        long mills = date.getTime();

        return new Date(mills);
    }

    public List<MatchOdds> findMostRecentMatchesAndOdds() {
        List<MatchOdds> mostCurrentOddsAndMatches = matchOddsJpaRepository.findAll();
        return mostCurrentOddsAndMatches;
    }
}
