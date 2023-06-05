package com.springintro.springboot.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookArrayModel {
    private long[] ids;

    public List<Long> getIds() {
        List<Long> idsList = Arrays.stream(ids)
                .boxed()
                .collect(Collectors.toList());
        return idsList;
    }
}
