package com.codingnomads.betty.data.batch.footballmatchjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
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

    private FootballMatchInfoService footballMatchInfoService;
    private boolean readState = false;

    @Autowired
    public FootballMatchApiReader(FootballMatchInfoService footballMatchInfoService) {
        this.footballMatchInfoService = footballMatchInfoService;
    }

    @Override
    public List<FootballMatchInfo> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(!readState){
            readState = true;
            return footballMatchInfoService.getFootballMatchListFromApi();
        }

        readState = false;

        return null;
    }
}
