package com.codingnomads.betty.logic.services;


import com.codingnomads.betty.data.models.Tweet;
import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import com.codingnomads.betty.logic.models.TeamSentimentScore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProcessTweetsThroughNlpServiceTest {

    @InjectMocks
    private ProcessTweetsThroughNlpService nlpService;

    @Mock
    TwitterJpaRepository mockTwitterJpaRepository;

    @Mock
    SourceToResultPipelineService mockSourceToResultPipelineService;


    @Test(expected = NullPointerException.class)
    public void ifKeywordUsedIsNull_throwNullPointerException() {

        String keywordUsed = null;
        nlpService.returnSentimentScoreByKeywordUsed(keywordUsed);
    }

    @Test
    public void givenAKeywordUsedIsNotNull_SentimentScoreIsReturnedBetween0and100() {
        String keywordUsed = "someText";

        TeamSentimentScore teamSentimentScore = new TeamSentimentScore();
        teamSentimentScore.setScore(10.0);
        List<Tweet> tweetList = new ArrayList<>();

        when(mockTwitterJpaRepository.findByKeywordUsedLike(any())).thenReturn(tweetList);
        when(mockSourceToResultPipelineService.convertTextsToSentimentResultList(any())).thenReturn(teamSentimentScore);


        Double score = nlpService.returnSentimentScoreByKeywordUsed(keywordUsed);
        assertThat(score).isBetween(0.0, 100.0);

    }

}
