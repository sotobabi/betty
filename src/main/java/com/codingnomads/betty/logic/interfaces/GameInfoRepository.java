package com.codingnomads.betty.logic.interfaces;

import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;

public interface GameInfoRepository {
    GameInformationJSON getGameInformation();
}
