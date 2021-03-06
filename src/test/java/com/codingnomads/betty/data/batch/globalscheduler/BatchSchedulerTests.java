package com.codingnomads.betty.data.batch.globalscheduler;

import com.codingnomads.betty.data.batch.BatchScheduler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BatchSchedulerTests {

    private JobLauncher mockJobLauncherForHomeTeamTweets;
    private JobLauncher mockJobLauncherForAwayTeamTweets;
    private JobLauncher mockJobLauncherForOdds;
    private JobLauncher mockJobLauncherForMatches;
    private Job mockJobForHomeTeamTweets;
    private Job mockJobForAwayTeamTweets;
    private Job mockJobForOdds;
    private Job mockJobForMatches;
    private BatchScheduler testBatchScheduler;
    private JobParameters jobParameters;
    private JobExecution mockJobExecution;
    private BatchStatus batchStatus;

    @Before
    public void setUp(){

        mockJobLauncherForHomeTeamTweets = mock(JobLauncher.class);
        mockJobLauncherForAwayTeamTweets = mock(JobLauncher.class);
        mockJobLauncherForOdds = mock(JobLauncher.class);
        mockJobLauncherForMatches = mock(JobLauncher.class);

        mockJobForHomeTeamTweets = mock(Job.class);
        mockJobForAwayTeamTweets = mock(Job.class);
        mockJobForOdds = mock(Job.class);
        mockJobForMatches = mock(Job.class);

        mockJobExecution = mock(JobExecution.class);

        jobParameters = new JobParameters();

        batchStatus = mockJobExecution.getStatus();

        testBatchScheduler = new BatchScheduler(mockJobLauncherForHomeTeamTweets, mockJobLauncherForAwayTeamTweets,
                mockJobLauncherForOdds, mockJobLauncherForMatches,
                mockJobForHomeTeamTweets, mockJobForAwayTeamTweets, mockJobForOdds, mockJobForMatches);
    }

    @Test
    public void whenRunTweetToDbJobScheduler_shouldReturnBatchStatus() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        when(mockJobLauncherForHomeTeamTweets.run(mockJobForHomeTeamTweets, jobParameters)).thenReturn(mockJobExecution);

        assertThat(testBatchScheduler.homeTeamTweetsToDbJobScheduler()).isEqualTo(batchStatus);
    }

    @Test
    public void whenRunOddsToDbJobScheduler_shouldReturnBatchStatus() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        when(mockJobLauncherForOdds.run(mockJobForOdds, jobParameters)).thenReturn(mockJobExecution);

        assertThat(testBatchScheduler.oddsToDbJobScheduler()).isEqualTo(batchStatus);
    }

    @Test
    public void whenRunFootballMatchesToDbJobScheduler_shouldReturnBatchStatus() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        when(mockJobLauncherForMatches.run(mockJobForMatches, jobParameters)).thenReturn(mockJobExecution);

        assertThat(testBatchScheduler.footballMatchesToDbJobScheduler()).isEqualTo(batchStatus);
    }
}
