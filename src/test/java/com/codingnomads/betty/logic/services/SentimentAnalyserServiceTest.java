package com.codingnomads.betty.logic.services;

import com.codingnomads.betty.logic.models.SentimentResult;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SentimentAnalyserServiceTest {

    private SentimentResult sentimentResult;


    @Mock
    private SentimentAnalyserService mockSentimentAnalyserService;


    @InjectMocks
    private StanfordCoreNLP pipeline;


    @Before
    public void setUp() {
        mockSentimentAnalyserService = new SentimentAnalyserService();
    }

    @Test(expected = NullPointerException.class)
    public void ifAverageSentimentResultScoreIsNull_throwANullPointerException() {
        List<SentimentResult> sentimentResultList = new ArrayList<SentimentResult>();
        sentimentResultList.add(0, sentimentResult);

        mockSentimentAnalyserService.getAverageSentimentScore(sentimentResultList);

    }

    @Test
    public void givenATextFile_ListOfSentimentScorePerSentenceIsDisplayed() {

        String fileName = "/home/pbx/Downloads/pride_and_prejudice_chapter1.txt";
        Path path = Paths.get(fileName);
        String text = "";

        try {
            Scanner scanner = new Scanner(path);

            System.out.println("Read text file using Scanner");
            //read line by line

            while(scanner.hasNextLine()) {
                //add each line to List
                text += scanner.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mockSentimentAnalyserService.getSentimentResult(text);

    }
}