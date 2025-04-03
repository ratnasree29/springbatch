package com.batch.ratna.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer, Long> {

    @Override
    public Long process(Integer integer) {
        System.out.println(integer);
        return (long) (integer + 20);
    }
}
