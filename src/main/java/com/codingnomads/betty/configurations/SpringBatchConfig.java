package com.codingnomads.betty.configurations;

import com.codingnomads.betty.data.models.FootballMatchInfo;
import com.codingnomads.betty.data.models.MatchOdds;
import com.codingnomads.betty.data.models.Tweet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import twitter4j.Status;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    @Order(1)
    @Bean("homeTeamTweets")
    public Job tweetJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<List<Status>> itemReader, ItemProcessor<List<Status>, List<Tweet>> itemProcessor,
                   ItemWriter<List<Tweet>> itemWriter) {

        Step homeTeamStep = stepBuilderFactory.get("read-process-save-home-team-tweets-in-db")
                .<List<Status>, List<Tweet>>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("get-home-team-tweets-from-api-and-save-to-db")
                .incrementer(new RunIdIncrementer())
                .start(homeTeamStep)
                .build();
    }

    @Order(2)
    @Bean("matches")
    public Job footballJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory
            ,@Qualifier("apiReader") ItemReader<List<FootballMatchInfo>> reader, ItemProcessor<List<FootballMatchInfo>
            , List<FootballMatchInfo>> processor
            ,ItemWriter<List<FootballMatchInfo>> writer){

        Step step = stepBuilderFactory.get("read-process-save-football-games-in-db")
                .<List<FootballMatchInfo>, List<FootballMatchInfo>> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("get-football-games-from-api-and-save-to-db")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Order(3)
    @Bean("odds")
    public Job oddToDbJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory
            , @Qualifier("jpaReader") ItemReader<List<FootballMatchInfo>> itemReader, ItemProcessor<List<FootballMatchInfo>, List<MatchOdds>> itemProcessor
            , ItemWriter<List<MatchOdds>> itemWriter){

        Step step = stepBuilderFactory.get("save-matches-and-odds-to-db")
                .<List<FootballMatchInfo>, List<MatchOdds>>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("transform-football-games-from-db-as-matches-and-odds")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
