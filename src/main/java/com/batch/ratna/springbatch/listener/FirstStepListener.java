package com.batch.ratna.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("step execution before");
        System.out.println(stepExecution.getJobParameters());
        stepExecution.getExecutionContext().put("steplistener", "steplistener");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("step execution after");
        System.out.println(stepExecution.getExecutionContext());
        return null;
    }
}
