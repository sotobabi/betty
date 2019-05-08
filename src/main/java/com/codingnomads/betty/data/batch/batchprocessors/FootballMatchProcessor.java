package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.logic.interfaces.FootballMatchesInfoJpaRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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

        for (FootballMatchInfo infoFromApi : listFromApi) {

            for (FootballMatchInfo infoFromDb : listFromDb) {

                if(infoFromApi.getApi_id().equals(infoFromDb.getApi_id())) {

                    System.out.println(infoFromDb.getName());

                    listFromApi.remove(infoFromDb);
                }
            }
        }

        System.out.println(listFromApi.size());

        return listFromApi;

    }
}
