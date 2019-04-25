package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SentimentAnalyserServiceTest {

    private SentimentAnalyserService service;
    private SentimentResult sentimentResult;

    @Before
    public void setUp() throws Exception {
        service = new SentimentAnalyserService();
    }


    @Test(expected = NullPointerException.class)
    public void ifAverageSentimentResultScoreIsNull_throwANullPointerException() {
        List<SentimentResult> sentimentResultList = new ArrayList<SentimentResult>();
        sentimentResultList.add(0,sentimentResult);

        service.getAverageSentimentScore(sentimentResultList);

    }
}