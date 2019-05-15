package com.codingnomads.betty.data.batch.footballmatchjob;

import com.codingnomads.betty.data.batch.footballmatchjob.exceptions.EmptyItemException;
import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FootballMatchRdsWriter implements ItemWriter<List<FootballMatchInfo>> {

    private FootballMatchInfoService footballMatchInfoService;

    @Autowired
    public FootballMatchRdsWriter(FootballMatchInfoService footballMatchInfoService) {
        this.footballMatchInfoService = footballMatchInfoService;
    }

    @Override
    public void write(List<? extends List<FootballMatchInfo>> items) throws Exception {

        if(items == null){
            throw new EmptyItemException("There are no new football matches to write!");
        }

        for (List<FootballMatchInfo> matchList : items) {

            footballMatchInfoService.saveFootballMatchList(matchList);
        }
    }
}
