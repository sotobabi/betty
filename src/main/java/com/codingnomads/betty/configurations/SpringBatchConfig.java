package com.codingnomads.betty.configurations;

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
import twitter4j.Status;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<List<Status>> itemReader, ItemProcessor<List<Status>, List<Tweet>> itemProcessor,
                   ItemWriter<List<Tweet>> itemWriter) {

        Step step = stepBuilderFactory.get("Sink-Tweets-To-DB")
                .<List<Status>, List<Tweet>>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("Get-Tweets-To-DB-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }



}
