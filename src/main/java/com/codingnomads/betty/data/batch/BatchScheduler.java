package com.codingnomads.betty.data.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class BatchScheduler {

    private JobLauncher jobLauncherForHomeTeamTweets;
    private JobLauncher jobLauncherForAwayTeamTweets;
    private JobLauncher jobLauncherForOdds;
    private JobLauncher jobLauncherForFootballGames;
    private Job jobForHomeTeamTweets;
    private Job jobForAwayTeamTweets;
    private Job jobForOdds;
    private Job jobForFootballGames;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncherForHomeTeamTweets, JobLauncher jobLauncherForAwayTeamTweets
            , JobLauncher jobLauncherForOdds
            , JobLauncher jobLauncherForFootballGames
            , @Qualifier("homeTeamTweets") Job jobForHomeTeamTweets
            , @Qualifier("awayTeamTweets") Job jobForAwayTeamTweets
            , @Qualifier("odds") Job jobForOdds
            , @Qualifier("matches") Job jobForFootballGames) {

        this.jobLauncherForHomeTeamTweets = jobLauncherForHomeTeamTweets;
        this.jobLauncherForAwayTeamTweets = jobLauncherForAwayTeamTweets;
        this.jobLauncherForOdds = jobLauncherForOdds;
        this.jobLauncherForFootballGames = jobLauncherForFootballGames;
        this.jobForHomeTeamTweets = jobForHomeTeamTweets;
        this.jobForAwayTeamTweets = jobForAwayTeamTweets;
        this.jobForOdds = jobForOdds;
        this.jobForFootballGames = jobForFootballGames;
    }

    @Scheduled(cron = "0 0 */6 ? * *")
    public BatchStatus awayTeamTweetsToDbJobScheduler() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = runAwayTeamTweetsJob(parameters);

        return getBatchStatus(jobExecution);
    }

    @Scheduled(cron = "0 0 */6 ? * *")
    public BatchStatus homeTeamTweetsToDbJobScheduler() throws JobParametersInvalidException,
           JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = runHomeTeamTweetsJob(parameters);

        return getBatchStatus(jobExecution);
    }

    @Scheduled(cron = "0 0 */6 ? * *")
    public BatchStatus oddsToDbJobScheduler() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = runOddsJob(parameters);

        return getBatchStatus(jobExecution);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public BatchStatus footballMatchesToDbJobScheduler() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = runFootballGamesJob(parameters);

        return getBatchStatus(jobExecution);
    }

    private JobParameters getJobParameters(){
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        return new JobParameters(maps);
    }

    private JobExecution runHomeTeamTweetsJob(JobParameters parameters) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return jobLauncherForHomeTeamTweets.run(jobForHomeTeamTweets, parameters);
    }

    private JobExecution runAwayTeamTweetsJob(JobParameters parameters) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return jobLauncherForAwayTeamTweets.run(jobForAwayTeamTweets, parameters);
    }

    private JobExecution runOddsJob(JobParameters parameters) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return jobLauncherForOdds.run(jobForOdds, parameters);
    }

    private JobExecution runFootballGamesJob(JobParameters parameters) throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return jobLauncherForFootballGames.run(jobForFootballGames, parameters);
    }

    private BatchStatus getBatchStatus(JobExecution jobExecution) {
        if (jobExecution != null) {
            return jobExecution.getStatus();
        } else {
            return null;
        }
    }
}
