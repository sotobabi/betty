package com.codingnomads.betty.configurations;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import twitter4j.Status;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Primary
    @Bean
    public Job tweetJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<List<Status>> itemReader, ItemProcessor<List<Status>, List<Tweet>> itemProcessor,
                   ItemWriter<List<Tweet>> itemWriter) {

        Step step = stepBuilderFactory.get("Sink-Tweets-To-DB")
                .<List<Status>, List<Tweet>>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("Get-Tweets-To-DB-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Job oddToDbJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory
            ,ItemReader<MatchOdds> itemReader, ItemProcessor<MatchOdds, MatchOdds> itemProcessor
            ,ItemWriter<MatchOdds> itemWriter){

        Step step = stepBuilderFactory.get("Saving Odds To Database")
                .<MatchOdds, MatchOdds>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("Match-Odds-To-DB")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }



}
