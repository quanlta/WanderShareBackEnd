package com.admin.admin.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CategoryChartReponse {
    private AtomicInteger longt;
    private AtomicInteger shortt;

    public CategoryChartReponse(AtomicInteger longt, AtomicInteger shortt) {
        this.longt = longt;
        this.shortt = shortt;
    }
}
