package com.codingnomads.betty.data.batch.scheduler;

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

    private JobLauncher jobLauncherForTweets;
    private JobLauncher jobLauncherForOdds;
    private JobLauncher jobLauncherForFootballGames;
    private Job jobForTweets;
    private Job jobForOdds;
    private Job jobForFootballGames;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncherForFootballGames, @Qualifier("matches")Job jobForFootballGames) {
        this.jobLauncherForFootballGames = jobLauncherForFootballGames;
        this.jobForFootballGames = jobForFootballGames;
    }


    //    @Autowired
//    public BatchScheduler(JobLauncher jobLauncherForTweets, JobLauncher jobLauncherForOdds
//            , JobLauncher jobLauncherForFootballGames
//            , @Qualifier("tweets") Job jobForTweets, @Qualifier("odds") Job jobForOdds
//            , @Qualifier("matches") Job jobForFootballGames) {
//
//        this.jobLauncherForTweets = jobLauncherForTweets;
//        this.jobLauncherForOdds = jobLauncherForOdds;
//        this.jobLauncherForFootballGames = jobLauncherForFootballGames;
//        this.jobForTweets = jobForTweets;
//        this.jobForOdds = jobForOdds;
//        this.jobForFootballGames = jobForFootballGames;
//    }

//   @Scheduled(cron = "0 0 */6 ? * *")
//    public BatchStatus tweetToDbJobScheduler() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
//        JobParameters parameters = getJobParameters();
//        JobExecution jobExecution = runTweetsJob(parameters);
//
//        return getBatchStatus(jobExecution);
//    }
//
//    @Scheduled(cron = "0 0 */6 ? * *")
//    public BatchStatus oddsToDbJobScheduler() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
//        JobParameters parameters = getJobParameters();
//        JobExecution jobExecution = runOddsJob(parameters);
//
//        return getBatchStatus(jobExecution);
//    }

    @Scheduled(cron = "0 0 0 * * *")
    public BatchStatus footballMatchesToDbJobScheduler() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = runFootballGamesJob(parameters);

        return getBatchStatus(jobExecution);
    }

    private JobParameters getJobParameters(){
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        return new JobParameters(maps);
    }

    private JobExecution runTweetsJob(JobParameters parameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        return jobLauncherForTweets.run(jobForTweets, parameters);

    }

    private JobExecution runOddsJob(JobParameters parameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        return jobLauncherForOdds.run(jobForOdds, parameters);

    }

    private JobExecution runFootballGamesJob(JobParameters parameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        return jobLauncherForFootballGames.run(jobForFootballGames, parameters);
    }

    private BatchStatus getBatchStatus(JobExecution jobExecution) {
        if (jobExecution!=null) {
            return jobExecution.getStatus();
        } else {
            return null;
        }
    }
}
