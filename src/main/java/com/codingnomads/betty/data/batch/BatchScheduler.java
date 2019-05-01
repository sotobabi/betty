package com.codingnomads.betty.data.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class BatchScheduler {

    private JobLauncher jobLauncherForTweets;
    private JobLauncher jobLauncherForOdds;
    private Job jobForTweets;
    private Job jobForOdds;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncherForTweets, JobLauncher jobLauncherForOdds, Job jobForTweets, Job jobForOdds) {
        this.jobLauncherForTweets = jobLauncherForTweets;
        this.jobLauncherForOdds = jobLauncherForOdds;
        this.jobForTweets = jobForTweets;
        this.jobForOdds = jobForOdds;
    }

    @Scheduled(cron = "*/50 * * * * *") //(cron = "0 0 */6 ? * *")
    public BatchStatus tweetToDbJobScheduler() {
        JobParameters parameters = getTweetJobParameters();
        JobExecution jobExecution = null;
        jobExecution = runTweetsJob(parameters, jobExecution);

        return getBatchStatus(jobExecution);
    }

    @Scheduled(cron = "*/50 * * * * *")
    public BatchStatus oddsToDbJobScheduler(){
        JobParameters parameters = getOddsJobParameters();
        JobExecution jobExecution = null;
        jobExecution = runOddsJob(parameters, jobExecution);

        return getBatchStatus(jobExecution);
    }

    private JobParameters getTweetJobParameters() {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        return new JobParameters(maps);
    }

    private JobParameters getOddsJobParameters(){
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        return new JobParameters(maps);
    }

    private JobExecution runTweetsJob(JobParameters parameters, JobExecution jobExecution) {
        try {
            jobExecution = jobLauncherForTweets.run(jobForTweets, parameters);
        } catch (JobExecutionAlreadyRunningException e) {
            System.out.println("EXCEPTION CAUGHT -> Job Already running...");
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            System.out.println("EXCEPTION CAUGHT -> Job Already Completed Exception...");
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            System.out.println("EXCEPTION CAUGHT -> Job Parameters are invalid");
            e.printStackTrace();
        } catch (JobRestartException e) {
            System.out.println("EXCEPTION CAUGHT -> Illegal attempt to restart jobForTweets...");
            e.printStackTrace();
        }
        return jobExecution;
    }

    private JobExecution runOddsJob(JobParameters parameters, JobExecution jobExecution){

        try {
            jobExecution = jobLauncherForOdds.run(jobForOdds, parameters);
        } catch (JobExecutionAlreadyRunningException e) {
            System.out.println("EXCEPTION CAUGHT -> Odds Job Already running...");
            e.printStackTrace();
        } catch (JobRestartException e) {
            System.out.println("EXCEPTION CAUGHT -> Illegal attempt to restart jobForOdds...");
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            System.out.println("EXCEPTION CAUGHT -> Odds Job Already Completed Exception...");
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            System.out.println("EXCEPTION CAUGHT -> Odd Job Parameters are invalid");
            e.printStackTrace();
        }

        return jobExecution;
    }

    private BatchStatus getBatchStatus(JobExecution jobExecution) {
        if (jobExecution!=null) {
            return jobExecution.getStatus();
        } else {
            return null;
        }
    }
}
