package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.GameInformationService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchOddsReader implements ItemReader<MatchOdds> {

    private GameInformationService gameInformationService;
    //TODO TEAMNAMES WILL NOT BE HARDCODED!!!
    private String homeTeam = "Arsenal";
    private String awayTeam = "Brighton & Hove Albion";
    private boolean batchJobState = false;

    @Autowired
    public MatchOddsReader(GameInformationService gameInformationService) {
        this.gameInformationService = gameInformationService;
    }

    @Override
    public MatchOdds read() {

        if(!batchJobState){
            batchJobState = true;
            return gameInformationService.getMatchOdds(homeTeam, awayTeam);
        }

        batchJobState = false;

        return null;
    }

    public boolean isBatchJobState() {
        return batchJobState;
    }

    public void setBatchJobState(boolean batchJobState) {
        this.batchJobState = batchJobState;
    }
}
