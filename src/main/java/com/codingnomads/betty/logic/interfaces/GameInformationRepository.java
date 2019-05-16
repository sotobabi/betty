package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.logic.models.betapimodels.EventJSON;
import com.codingnomads.betty.logic.models.betapimodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betapimodels.MarketJSON;
import com.codingnomads.betty.logic.models.betapimodels.RunnerJSON;

public interface GameInformationRepository {
    GameInformationJSON getGameInformation();
    EventJSON getEventJSONforMatch(String match, GameInformationJSON gameInformationJSON);
    MarketJSON getMarketJSONforMatch(EventJSON eventJSON);
    RunnerJSON getRunnerJSONforMarket(MarketJSON marketJSON, String teamName);
}
