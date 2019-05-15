package com.codingnomads.betty.data.batch.footballmatchjob;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.services.FootballMatchInfoService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FootballMatchProcessor implements ItemProcessor<List<FootballMatchInfo>, List<FootballMatchInfo>> {

    private FootballMatchInfoService footballMatchInfoService;

    @Autowired
    public FootballMatchProcessor(FootballMatchInfoService footballMatchInfoService) {
        this.footballMatchInfoService = footballMatchInfoService;
    }

    @Override
    public List<FootballMatchInfo> process(List<FootballMatchInfo> listFromApi) throws Exception {

        List<FootballMatchInfo> listFromDb = footballMatchInfoService.findLatestFootballMatchesFromDb();

        List<FootballMatchInfo> uniqueList = new ArrayList<>();

        for (FootballMatchInfo apiInfo : listFromApi) {

            boolean flag = false;

            for (FootballMatchInfo dbInfo : listFromDb) {

                if(apiInfo.getApi_id().equals(dbInfo.getApi_id())){

                    flag = true;
                }
            }

            if(!flag){
                uniqueList.add(apiInfo);
            }
        }

        return uniqueList;
    }
}
