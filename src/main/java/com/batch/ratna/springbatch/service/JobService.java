package com.batch.ratna.springbatch.service;

import com.batch.ratna.springbatch.model.JobParamsRequest;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;

    @Async
    public void startJob(String jobName, List<JobParamsRequest> jobParams) {
        Map<String, JobParameter> params = new HashMap<>();
        jobParams.forEach(request -> {
            params.put(request.getParamKey(), new JobParameter(request.getParamValue()));
        });
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = null;
        try {
            if (jobName.equals("First Job")) {
                jobExecution = jobLauncher.run(firstJob, jobParameters);
            } else {
                jobExecution = jobLauncher.run(secondJob, jobParameters);
            }
            System.out.println(jobExecution.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
