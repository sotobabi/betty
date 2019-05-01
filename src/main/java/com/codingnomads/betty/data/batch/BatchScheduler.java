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

    private JobLauncher jobLauncher;
    private Job job;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Scheduled(cron = "0 0 0/6 ? * * *")
    public BatchStatus tweetToDbJobScheduler() {
        JobParameters parameters = getJobParameters();
        JobExecution jobExecution = null;
        jobExecution = runTweetsJob(parameters, jobExecution);

        return getBatchStatus(jobExecution);
    }

    private JobParameters getJobParameters() {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        return new JobParameters(maps);
    }

    private JobExecution runTweetsJob(JobParameters parameters, JobExecution jobExecution) {
        try {
            jobExecution = jobLauncher.run(job, parameters);
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
            System.out.println("EXCEPTION CAUGHT -> Illegal attempt to restart job...");
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
