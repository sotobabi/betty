package com.codingnomads.betty.data.batch.matchoddsjob;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.services.MatchOddsService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchOddsRdsWriter implements ItemWriter<List<MatchOdds>>{

    private MatchOddsService matchOddsService;

    @Autowired
    public MatchOddsRdsWriter(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @Override
    public void write(List<? extends List<MatchOdds>> items) throws Exception {

        for (List<MatchOdds> list : items) {
            matchOddsService.saveMatchOddsList(list);
        }
    }
}
