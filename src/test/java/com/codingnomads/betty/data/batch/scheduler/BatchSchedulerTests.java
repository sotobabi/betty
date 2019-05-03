package com.codingnomads.betty.data.batch.scheduler;

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

    private JobLauncher mockJobLauncherForTweets;
    private JobLauncher mockJobLauncherForOdds;
    private Job mockJobForTweets;
    private Job mockJobForOdds;
    private BatchScheduler testBatchScheduler;
    private JobParameters jobParameters;
    private JobExecution mockJobExecution;
    private BatchStatus batchStatus;

    @Before
    public void setUp(){

        mockJobLauncherForTweets = mock(JobLauncher.class);
        mockJobLauncherForOdds = mock(JobLauncher.class);
        mockJobForTweets = mock(Job.class);
        mockJobForOdds = mock(Job.class);
        mockJobExecution = mock(JobExecution.class);
        jobParameters = new JobParameters();
        batchStatus = mockJobExecution.getStatus();
        testBatchScheduler = new BatchScheduler(mockJobLauncherForTweets, mockJobLauncherForOdds
                , mockJobForTweets, mockJobForOdds);
    }

    @Test
    public void whenRunTweetToDbJobScheduler_shouldReturnBatchStatus() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        when(mockJobLauncherForTweets.run(mockJobForTweets, jobParameters)).thenReturn(mockJobExecution);

        assertThat(testBatchScheduler.tweetToDbJobScheduler()).isEqualTo(batchStatus);
    }

    @Test
    public void whenRunOddsToDbJobScheduler_shouldReturnBatchStatus() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        when(mockJobLauncherForOdds.run(mockJobForOdds, jobParameters)).thenReturn(mockJobExecution);

        assertThat(testBatchScheduler.oddsToDbJobScheduler()).isEqualTo(batchStatus);
    }
}
