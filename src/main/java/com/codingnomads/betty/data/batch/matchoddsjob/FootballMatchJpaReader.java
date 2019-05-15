package com.codingnomads.betty.data.batch.matchoddsjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Qualifier("jpaReader")
@Component
public class FootballMatchJpaReader implements ItemReader<List<FootballMatchInfo>> {

    private FootballMatchInfoService footballMatchInfoService;
    private boolean batchJobState = false;

    @Autowired
    public FootballMatchJpaReader(FootballMatchInfoService footballMatchInfoService) {
        this.footballMatchInfoService = footballMatchInfoService;
    }

    @Override
    public List<FootballMatchInfo> read() {

        if(!batchJobState){
            batchJobState = true;
            return footballMatchInfoService.findLatestFootballMatchesFromDb();
        }

        batchJobState = false;

        return null;
    }
}


