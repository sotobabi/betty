package com.codingnomads.betty.data.batch.batchreaders;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Qualifier("jpaReader")
@Component
public class FootballMatchJpaReader implements ItemReader<List<FootballMatchInfo>> {

    private FootballMatchesInfoJpaRepository matchesInfoJpaRepository;
    private boolean batchJobState = false;

    @Autowired
    public FootballMatchJpaReader(FootballMatchesInfoJpaRepository matchesInfoJpaRepository) {

        this.matchesInfoJpaRepository = matchesInfoJpaRepository;
    }

    @Override
    public List<FootballMatchInfo> read() {

        if(!batchJobState){
            batchJobState = true;
            return matchesInfoJpaRepository.findByMatch_Date();
        }

        batchJobState = false;

        return null;
    }
}


