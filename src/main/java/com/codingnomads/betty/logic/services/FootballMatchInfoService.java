package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import com.codingnomads.betty.logic.interfaces.GameInformationRepository;
import com.codingnomads.betty.logic.models.betAPImodels.EventJSON;
import com.codingnomads.betty.logic.models.betAPImodels.GameInformationJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FootballMatchInfoService {

    private GameInformationRepository gameInformationRepository;
    private FootballMatchesInfoJpaRepository matchesInfoJpaRepository;

    @Autowired
    public FootballMatchInfoService(GameInformationRepository gameInformationRepository
            , FootballMatchesInfoJpaRepository matchesInfoJpaRepository) {

        this.gameInformationRepository = gameInformationRepository;
        this.matchesInfoJpaRepository = matchesInfoJpaRepository;
    }

    public List<FootballMatchInfo> getFootballMatchListFromApi() throws ParseException {

        GameInformationJSON gameInformation = gameInformationRepository.getGameInformation();

        List<FootballMatchInfo> footballMatchInfoList = new ArrayList<>();

        for (EventJSON event :gameInformation.getEventJSONS()) {

            FootballMatchInfo footballMatchInfo = new FootballMatchInfo();

            footballMatchInfo.setApi_id(event.getId());

            Instant instant = Instant.parse(event.getStart());

            footballMatchInfo.setMatchDate(Date.from(instant));

            footballMatchInfo.setName(event.getName());

            footballMatchInfoList.add(footballMatchInfo);
        }

        return footballMatchInfoList;
    }

    public List<FootballMatchInfo> findLatestFootballMatchesFromDb(){

        return matchesInfoJpaRepository.findByMatch_Date();
    }

    public List<FootballMatchInfo> saveFootballMatchList(List<FootballMatchInfo> list){

        return matchesInfoJpaRepository.saveAll(list);
    }
}
