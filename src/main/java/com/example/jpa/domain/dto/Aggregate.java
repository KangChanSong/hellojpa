package com.example.jpa.domain.dto;

import lombok.Getter;

@Getter
public class Aggregate {
    private Long count;
    private Long sum;
    private Double avg;
    private Integer max;
    private Integer min;

    public Aggregate(Long count, Long sum, Double avg, Integer max, Integer min) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
    }
}
