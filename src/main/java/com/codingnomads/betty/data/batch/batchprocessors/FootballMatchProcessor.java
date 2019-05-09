package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FootballMatchProcessor implements ItemProcessor<List<FootballMatchInfo>, List<FootballMatchInfo>> {

    private FootballMatchesInfoJpaRepository infoJpaRepository;

    @Autowired
    public FootballMatchProcessor(FootballMatchesInfoJpaRepository infoJpaRepository) {
        this.infoJpaRepository = infoJpaRepository;
    }

    @Override
    public List<FootballMatchInfo> process(List<FootballMatchInfo> listFromApi) throws Exception {

        List<FootballMatchInfo> listFromDb = infoJpaRepository.findByMatch_Date();

        Iterator<FootballMatchInfo> apiIterator = listFromApi.iterator();

        List<FootballMatchInfo> uniqueList = new ArrayList<>();

        while(apiIterator.hasNext()){

            FootballMatchInfo infoApi = apiIterator.next();

            boolean flag = false;

            Iterator<FootballMatchInfo> dbIterator = listFromDb.iterator();

            while(dbIterator.hasNext()){

                FootballMatchInfo infoDb = dbIterator.next();

                if(infoApi.getApi_id().equals(infoDb.getApi_id())){

                    flag = true;
                }
            }

            if(!flag){

                uniqueList.add(infoApi);
            }
        }

        return uniqueList;
    }
}
