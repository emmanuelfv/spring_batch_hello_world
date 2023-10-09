package com.batch.demo.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class jobConfig {
    @Autowired
    Step step;

    @Bean
    public Job create_job(JobRepository jobRepository){
        return new JobBuilder("job1", jobRepository)
                    .preventRestart().start(step).build();
    }

}
