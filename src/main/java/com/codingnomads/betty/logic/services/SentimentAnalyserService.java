package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.exceptions.InvalidInputException;
import com.codingnomads.betty.logic.models.SentimentClassification;
import com.codingnomads.betty.logic.models.SentimentResult;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.apache.commons.lang3.StringUtils;
import org.ejml.simple.SimpleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class SentimentAnalyserService {

    @Autowired
    @Qualifier("sentimentProperties")
    private Properties properties;
    @Autowired
    private StanfordCoreNLP pipeline;

    public List<SentimentResult> sentimentResultList = new ArrayList<>();

    public SentimentResult getSentimentResult(String text) {
        validateInput(text);

        return getSentimentResult(pipeline.process(text));
    }

    private void validateInput(String text) {
        if (StringUtils.isBlank(text)) {
            throw new InvalidInputException("Invalid input length");
        }
    }

    private SentimentResult getSentimentResult(Annotation annotation) {

        SentimentResult sentimentResult = null;
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sentimentResult = new SentimentResult();

            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            sentimentResult.setSentimentType(sentence.get(SentimentCoreAnnotations.SentimentClass.class));
            sentimentResult.setSentimentClass(getClassification(RNNCoreAnnotations.getPredictions(tree)));
            sentimentResult.setSentimentScore(RNNCoreAnnotations.getPredictedClass(tree));
            sentimentResultList.add(sentimentResult);

        }
        return sentimentResult;
    }

    public double getAverageSentimentScore(List<SentimentResult> sentimentResultList) {
        double averageSentimentScore = 0.0;

        for (SentimentResult sentimentResult : sentimentResultList) {
            averageSentimentScore += sentimentResult.getSentimentScore() * 25.0;
        }

        averageSentimentScore = averageSentimentScore / sentimentResultList.size();

        return averageSentimentScore;
    }

    private SentimentClassification getClassification(SimpleMatrix simpleMatrix) {
        SentimentClassification classification = new SentimentClassification();
        classification.setVeryNegative((int) Math.round(simpleMatrix.get(0) * 100d));
        classification.setNegative((int) Math.round(simpleMatrix.get(1) * 100d));
        classification.setNeutral((int) Math.round(simpleMatrix.get(2) * 100d));
        classification.setPositive((int) Math.round(simpleMatrix.get(3) * 100d));
        classification.setVeryPositive((int) Math.round(simpleMatrix.get(4) * 100d));
        return classification;
    }
}
