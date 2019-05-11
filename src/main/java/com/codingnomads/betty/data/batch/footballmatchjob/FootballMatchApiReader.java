package com.codingnomads.betty.data.batch.footballmatchjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Qualifier("apiReader")
@Component
public class FootballMatchApiReader implements ItemReader<List<FootballMatchInfo>> {

    private GameInformationService gameInformationService;
    private boolean readState = false;

    @Autowired
    public FootballMatchApiReader(GameInformationService gameInformationService) {
        this.gameInformationService = gameInformationService;
    }

    @Override
    public List<FootballMatchInfo> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(!readState){
            readState = true;
            return gameInformationService.getFootballMatchListFromApi();
        }

        readState = false;

        return null;
    }
}
