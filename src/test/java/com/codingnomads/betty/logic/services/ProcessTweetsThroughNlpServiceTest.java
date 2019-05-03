package com.codingnomads.betty.logic.services;


import com.codingnomads.betty.logic.interfaces.TwitterJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTweetsThroughNlpServiceTest {

    private ProcessTweetsThroughNlpService nlpService;

    @Mock
    TwitterJpaRepository mockTwitterJpaRepository;

    @Mock
    SourceToResultPipelineService mockSourceToResultPipelineService;

    @Before
    public void setUp() {
        nlpService = new ProcessTweetsThroughNlpService(mockTwitterJpaRepository, mockSourceToResultPipelineService);
    }

    @Test(expected = NullPointerException.class)
    public void ifKeywordUsedIsNull_throwNullPointerException() {

        String keywordUsed = null;
        nlpService.returnSentimentScoreByKeywordUsed(keywordUsed);
    }

    @Test
    public void givenAKeywordUsedIsNotNull_SentimentScoreIsReturnedBetween0and100() {
        String keywordUsed = "someText";

        assertThat(nlpService.returnSentimentScoreByKeywordUsed(keywordUsed)).isBetween(0.0, 100.0);

    }

}
