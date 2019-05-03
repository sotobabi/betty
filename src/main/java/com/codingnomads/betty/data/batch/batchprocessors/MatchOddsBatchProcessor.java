package com.codingnomads.betty.data.batch.batchprocessors;

import com.codingnomads.betty.data.models.MatchOdds;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MatchOddsBatchProcessor implements ItemProcessor<MatchOdds, MatchOdds> {

    @Override
    public MatchOdds process(MatchOdds item){

        return item;
    }
}

