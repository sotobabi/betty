package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SentimentAnalyserServiceTest {

    private SentimentAnalyserService service;
    private SentimentResult sentimentResult;

    @Before
    public void setUp() {
        service = new SentimentAnalyserService();
    }


    @Test(expected = NullPointerException.class)
    public void ifAverageSentimentResultScoreIsNull_throwANullPointerException() {
        List<SentimentResult> sentimentResultList = new ArrayList<SentimentResult>();
        sentimentResultList.add(0, sentimentResult);

        service.getAverageSentimentScore(sentimentResultList);

    }
}