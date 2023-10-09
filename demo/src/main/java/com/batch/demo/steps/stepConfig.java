package com.batch.demo.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class stepConfig {

    @Autowired
    private ItemReader<Integer> reader;

    @Autowired
    private ItemProcessor<Integer,String> processor;

    @Autowired
    private ItemWriter<String> writer;

    /*
     *If there are more than 1 step use
     *  import org.springframework.beans.factory.annotation.Qualifier;
     *
     *  @Qualifier("step1")
     */
    @Bean
    public Step create_step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step1", jobRepository)
                        .<Integer,String>chunk(1, transactionManager)
                        .reader(reader).writer(writer).processor(processor)
                        .build();
    }
}
