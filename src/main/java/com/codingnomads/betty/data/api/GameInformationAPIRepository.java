package com.codingnomads.betty.data.api;

import com.codingnomads.betty.logic.exceptions.JSONNotFoundException;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betapimodels.EventJSON;
import com.codingnomads.betty.logic.models.betapimodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betapimodels.MarketJSON;
import com.codingnomads.betty.logic.models.betapimodels.RunnerJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GameInformationAPIRepository implements GameInformationRepository {

    private RestTemplate restTemplate;

    @Autowired
    public GameInformationAPIRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GameInformationJSON getGameInformation(){
        String url = "https://api.matchbook.com/edge/rest/events?tag-url-names=united-states-of-america&sport-ids=15&" +
                "states=open&exchange-type=back-lay&odds-type=DECIMAL&include-prices=false&" +
                "price-depth=1&price-mode=aggregated&side=lay&currency=GBP&minimum-liquidity=10&" +
                "include-event-participants=true&market-types=one_x_two&per-page=500";

        return restTemplate.getForObject(url, GameInformationJSON.class);
    }

    @Override
    public EventJSON getEventJSONforMatch(String match, GameInformationJSON gameInformationJSON) {
        for (EventJSON event : gameInformationJSON.getEventJSONS()) {
            if (event.getName().equalsIgnoreCase(match)) {
                return event;
            }
        }

        throw new JSONNotFoundException("Game Not Found!");
    }

    @Override
    public MarketJSON getMarketJSONforMatch(EventJSON eventJSON){
        for (MarketJSON marketJSON: eventJSON.getMarketJSONS()) {
            if(marketJSON.getName().equalsIgnoreCase("Match Odds")){
                return marketJSON;
            }
        }
        throw new JSONNotFoundException("There are No Match Odds For This Game!");
    }

    @Override
    public RunnerJSON getRunnerJSONforMarket(MarketJSON marketJSON, String teamName){
        for (RunnerJSON runnerJSON : marketJSON.getRunnerJSONS()){
            if(runnerJSON.getName().equalsIgnoreCase(teamName)){
                return runnerJSON;
            }
        }
        throw new JSONNotFoundException("Team Not Found in RunnerJSON!");
    }
}
