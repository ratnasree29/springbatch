package com.batch.ratna.springbatch.config;

import com.batch.ratna.springbatch.listener.FirstJobListener;
import com.batch.ratna.springbatch.listener.FirstStepListener;
import com.batch.ratna.springbatch.processor.FirstItemProcessor;
import com.batch.ratna.springbatch.reader.FirstItemReader;
import com.batch.ratna.springbatch.service.SecondTaskletService;
import com.batch.ratna.springbatch.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SecondTaskletService secondTaskletService;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }

    private Step firstStep() {
        return stepBuilderFactory.get("First Step")
                .listener(firstStepListener)
                .tasklet(firstTask())
                .build();
    }

    private Step secondStep() {
        return stepBuilderFactory.get("Second Step")
                .listener(firstStepListener)
                .tasklet(secondTaskletService)
                .build();
    }

    private Tasklet firstTask() {
        return (contribution, chunkContext) -> {
            System.out.println("This is first tasklet step");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job secondJob() {
        return jobBuilderFactory.get("Second Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(secondStep())
                .build();
    }

    private Step firstChunkStep() {
        return stepBuilderFactory.get("First chunk Step")
                .<Integer, Long>chunk(2)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
