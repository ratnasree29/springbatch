package com.batch.ratna.springbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> list) {
        System.out.println(list.size());
        list.forEach(System.out::println);
    }
}
