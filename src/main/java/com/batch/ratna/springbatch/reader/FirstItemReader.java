package com.batch.ratna.springbatch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {

    List<Integer> list = Arrays.asList(2, 6, 8, 9, 12);
    int i = 0;

    @Override
    public Integer read() {
        System.out.println("Inside item reader");
        Integer item;
        if (i < list.size()) {
            item = list.get(i);
            i++;
            return item;
        }
        i = 0;
        return null;
    }
}
