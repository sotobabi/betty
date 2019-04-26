package com.codingnomads.betty.configurations;

import com.codingnomads.betty.logic.models.SentimentClassification;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SentimentAnalysisConfig {

    @Bean(name = "sentimentProperties")
    public Properties setProperties() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        return properties;
    }

    @Bean
    public StanfordCoreNLP createStanfordCoreNlp() {
        return new StanfordCoreNLP(setProperties());
    }

    @Bean
    public SentimentClassification createSentimentClassification() {
        return new SentimentClassification();
    }
}
