package com.codingnomads.betty.data.api;

import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
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

        String url = "https://api.matchbook.com/edge/rest/events?tag-url-names=premier-league&sport-ids=15&states=open&exchange-type=back-lay&odds-type=DECIMAL&include-prices=false&price-depth=1&price-mode=aggregated&side=lay&currency=GBP&minimum-liquidity=10&include-event-participants=true&market-types=one_x_two&per-page=500";

        return restTemplate.getForObject(url, GameInformationJSON.class);
    }
}
