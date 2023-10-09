package com.batch.demo.steps;

import java.util.Random;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class stepItems {
    
    @Bean
    public ItemReader<Integer> create_item_reader(){
        
        return new ItemReader<Integer>() {
            @Override
            public Integer read(){
                return new Random().nextInt();
            }
        };
    }

    @Bean
    public ItemProcessor<Integer,String> create_procesor(){
        return new ItemProcessor<Integer,String>() {
            @Override
            public String process(Integer item) {
                return "processed: " + item;
            }
        };
    }

    @Bean 
    public ItemWriter<String> create_item_writer(){
        return new ItemWriter<String>(){
            @Override
            public void write(Chunk<? extends String> chunk) throws Exception {
                chunk.forEach(System.out::println);
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }
        };
    }

}
