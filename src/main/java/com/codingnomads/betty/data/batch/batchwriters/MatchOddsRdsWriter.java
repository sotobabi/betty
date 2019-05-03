package com.codingnomads.betty.data.batch.batchwriters;

import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.logic.interfaces.MatchOddsJpaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchOddsRdsWriter implements ItemWriter<MatchOdds>{

    private MatchOddsJpaRepository matchOddsJpaRepository;

    @Autowired
    public MatchOddsRdsWriter(MatchOddsJpaRepository matchOddsJpaRepository) {
        this.matchOddsJpaRepository = matchOddsJpaRepository;
    }

    @Override
    public void write(List<? extends MatchOdds> items){

        matchOddsJpaRepository.save(items.get(0));
    }
}
