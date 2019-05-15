package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import com.codingnomads.betty.logic.models.betAPImodels.MarketJSON;
import com.codingnomads.betty.logic.models.betAPImodels.RunnerJSON;

public interface GameInformationRepository {
    GameInformationJSON getGameInformation();
    EventJSON getEventJSONforMatch(String match, GameInformationJSON gameInformationJSON);
    MarketJSON getMarketJSONforMatch(EventJSON eventJSON);
    RunnerJSON getRunnerJSONforMarket(MarketJSON marketJSON, String teamName);
}
