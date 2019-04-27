package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betAPImodels.MarketJSON;
import com.codingnomads.betty.logic.models.betAPImodels.RunnerJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameInformationService {

    private GameInformationRepository gameInformationRepository;

    @Autowired
    public GameInformationService(GameInformationRepository gameInformationRepository) {
        this.gameInformationRepository = gameInformationRepository;
    }

    public Map<String, Double> getOddsByMatch(String team1, String team2) {

        GameInformationJSON gameInformationJSON = getGameInformation();

        String match = team1 + " vs " + team2;

        EventJSON eventJSON = getEventJSONforMatch(match, gameInformationJSON);

        MarketJSON marketJSON = getMarketJSONforMatch(eventJSON);

        Double oddForTeam1 = getRunnerJSONforMarket(marketJSON, team1).getPriceJSONS().get(0).getOdds();
        Double oddForTeam2 = getRunnerJSONforMarket(marketJSON, team2).getPriceJSONS().get(0).getOdds();

        HashMap<String, Double> odds = new HashMap();

        odds.put(team1, oddForTeam1);
        odds.put(team2, oddForTeam2);

        return odds;
    }

    private GameInformationJSON getGameInformation() {

        return gameInformationRepository.getGameInformation();
    }

    private EventJSON getEventJSONforMatch(String match, GameInformationJSON gameInformationJSON) {

        for (EventJSON eventJSON : gameInformationJSON.getEventJSONS()) {

            if (eventJSON.getName().equalsIgnoreCase(match)) {

                return eventJSON;
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
}
