package com.batch.ratna.springbatch.controller;

import com.batch.ratna.springbatch.model.JobParamsRequest;
import com.batch.ratna.springbatch.service.JobService;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParams) {
        jobService.startJob(jobName, jobParams);
        return "Job started";
    }
    @GetMapping("/stop/{executionId}")
    public String stopJob(@PathVariable long executionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
        jobOperator.stop(executionId);
        return "Job stopped";
    }
}
