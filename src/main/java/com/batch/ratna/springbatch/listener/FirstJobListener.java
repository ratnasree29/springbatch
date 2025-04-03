package com.batch.ratna.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before Job " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job params " + jobExecution.getJobParameters());
        System.out.println("Job Exec context " + jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("dummy", "dummy value");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Job " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job params " + jobExecution.getJobParameters());
        System.out.println("Job Exec context " + jobExecution.getExecutionContext());
    }
}
