package com.batch.ratna.springbatch.scheduler;

import com.batch.ratna.springbatch.model.JobParamsRequest;
import com.batch.ratna.springbatch.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecondJobScheduler {

    @Autowired
    private JobService jobService;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobScheduler() {
        List<JobParamsRequest> request = new ArrayList<>();
        JobParamsRequest req = new JobParamsRequest("1234", "job1234");
        request.add(req);
        jobService.startJob("secondJob", request);
    }
}
